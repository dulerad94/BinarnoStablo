public class BinarnoStablo {
	CvorBinarnogStabla koren;

	public CvorBinarnogStabla getKoren() {
		return koren;
	}

	public void setKoren(CvorBinarnogStabla koren) {
		this.koren = koren;
	}

	public void ubaci(String vrednost) {

		if (koren == null) {
			koren = new CvorBinarnogStabla(vrednost, null, null);
			return;
		}
		CvorBinarnogStabla novi = new CvorBinarnogStabla(vrednost, null, null);
		CvorBinarnogStabla pom = koren;
		while ((novi.getKljuc() < pom.getKljuc() || pom.getDesni() != null)
				&& (novi.getKljuc() > pom.getKljuc() || pom.getLevi() != null)) {
			if (novi.getKljuc() > pom.getKljuc()) {
				pom = pom.getDesni();
			} else if (novi.getKljuc() < pom.getKljuc()) {
				pom = pom.getLevi();
			} else
				return;
		}
		if (novi.getKljuc() > pom.getKljuc()) {
			pom.setDesni(novi);
		} else if (novi.getKljuc() < pom.getKljuc()) {
			pom.setLevi(novi);
		}
			balansiraj(koren);
	}

	public void izbaci(String vrednost) {
		CvorBinarnogStabla cvor = pronadjiCvor(
				CvorBinarnogStabla.hashCode(vrednost), koren);
		if (cvor == null) {
			System.out.println("Ne nalazi se u stablu");
			return;
		}
		if (!punList(cvor)) {
			CvorBinarnogStabla roditelj = pronadjiRoditelja(cvor, koren);
			if (cvor.getKljuc() > roditelj.getKljuc()) {
				roditelj.setDesni((cvor.getDesni() == null ? cvor.getLevi()
						: cvor.getDesni()));
			} else {
				roditelj.setLevi((cvor.getDesni() == null ? cvor.getLevi()
						: cvor.getDesni()));
			}
		} else {
			zameniSaLevimNajvecim(cvor);
			izbaci(cvor.getVrednost());
		}
		balansiraj(koren);
	}

	public String pronadji(int kljuc, CvorBinarnogStabla koren) {
		if (koren == null)
			return null;
		if (koren.getKljuc() == kljuc)
			return koren.getVrednost();
		if (koren.getKljuc() > kljuc)
			return pronadji(kljuc, koren.getLevi());
		return pronadji(kljuc, koren.getDesni());
	}

	private CvorBinarnogStabla pronadjiCvor(int kljuc, CvorBinarnogStabla koren) {
		if (koren == null || koren.getKljuc() == kljuc) {
			return koren;
		}
		if (koren.getKljuc() > kljuc)
			return pronadjiCvor(kljuc, koren.getLevi());
		return pronadjiCvor(kljuc, koren.getDesni());
	}

	private CvorBinarnogStabla pronadjiRoditelja(CvorBinarnogStabla cvor,
			CvorBinarnogStabla koren) {
		if (koren == null || cvor == koren)
			return null;
		if ((koren.getLevi() != null && koren.getLevi().getKljuc() == cvor
				.getKljuc())
				|| koren.getDesni() != null
				&& koren.getDesni().getKljuc() == cvor.getKljuc()) {
			return koren;
		}
		if (koren.getKljuc() > cvor.getKljuc())
			return pronadjiRoditelja(cvor, koren.getLevi());
		return pronadjiRoditelja(cvor, koren.getDesni());
	}

	private void zameniSaLevimNajvecim(CvorBinarnogStabla cvor) {
		CvorBinarnogStabla najveciLevi = najveci(cvor.getLevi());
		String pom = cvor.getVrednost();
		cvor.setVrednost(najveciLevi.getVrednost());
		najveciLevi.setVrednost(pom);
	}

	private CvorBinarnogStabla najveci(CvorBinarnogStabla koren) {
		if (koren == null)
			return null;
		while (koren.getDesni() != null) {
			koren = koren.getDesni();
		}
		return koren;
	}

	public void prefix(CvorBinarnogStabla koren) {
		if (koren == null)
			return;
		System.out.println(koren.getVrednost());
	//	System.out.println(koren.getKljuc());
		prefix(koren.getLevi());
		prefix(koren.getDesni());
	}

	public void infix(CvorBinarnogStabla koren) {
		if (koren == null)
			return;
		infix(koren.getLevi());
		System.out.println(koren.getVrednost());
	//	System.out.println(koren.getKljuc());
		infix(koren.getDesni());
	}

	public void postfix(CvorBinarnogStabla koren) {
		if (koren == null)
			return;
		postfix(koren.getLevi());
		postfix(koren.getDesni());
		System.out.println(koren.getVrednost());
	//	System.out.println(koren.getKljuc());
	}

	public int visina(CvorBinarnogStabla koren) {
		if (koren == null)
			return 0;
		return 1 + Math.max(visina(koren.getDesni()), visina(koren.getLevi()));
	}

	private void balansiraj(CvorBinarnogStabla koren) {
		if (koren == null)
			return;
		balansiraj(koren.getLevi());
		balansiraj(koren.getDesni());
		if (visina(koren.getLevi()) - visina(koren.getDesni()) > 1) {
			if (koren.getLevi() != null
					&& visina(koren.getLevi().getLevi())
							- visina(koren.getLevi().getDesni()) >= 0) {
				rotirajUdesno(koren, koren.getLevi());
			} else if (koren.getLevi() != null
					&& visina(koren.getLevi().getLevi())
							- visina(koren.getLevi().getDesni()) < 0) {
				rotirajUlevo(koren.getLevi(), koren.getLevi().getDesni());
				rotirajUdesno(koren, koren.getLevi());
			}
		} else if (visina(koren.getLevi()) - visina(koren.getDesni()) < -1) {
			if (koren.getDesni() != null
					&& visina(koren.getDesni().getLevi())
							- visina(koren.getDesni().getDesni()) <= 0) {
				rotirajUlevo(koren, koren.getDesni());
			} else if (koren.getDesni() != null
					&& visina(koren.getDesni().getLevi())
							- visina(koren.getDesni().getDesni()) > 0) {
				rotirajUdesno(koren.getDesni(), koren.getDesni().getLevi());
				rotirajUlevo(koren, koren.getDesni());
			}
		}

	}

	private void rotirajUdesno(CvorBinarnogStabla k1, CvorBinarnogStabla k2) {
		CvorBinarnogStabla roditelj = pronadjiRoditelja(k1, koren);
		if (roditelj != null && roditelj.getDesni() == k1) {
			roditelj.setDesni(k2);
		} else if (roditelj != null && roditelj.getLevi() == k1) {
			roditelj.setLevi(k2);
		}else{
			koren=k2;
		}
			k1.setLevi(k2.getDesni());
			k2.setDesni(k1);
			
		
	}

	private void rotirajUlevo(CvorBinarnogStabla k1, CvorBinarnogStabla k2) {
		CvorBinarnogStabla roditelj = pronadjiRoditelja(k1, koren);
		if (roditelj != null && roditelj.getDesni() == k1) {
			roditelj.setDesni(k2);
		} else if (roditelj != null && roditelj.getLevi() == k1) {
			roditelj.setLevi(k2);
		}else {
			koren=k2;
		}
			k1.setDesni(k2.getLevi());
			k2.setLevi(k1);		
	}

	private boolean punList(CvorBinarnogStabla k) {
		if (k == null || k.getDesni() == null || k.getLevi() == null)
			return false;
		return true;
	}
}
