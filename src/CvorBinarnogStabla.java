
public class CvorBinarnogStabla {
	 private int kljuc;
	 private String vrednost;
	 private CvorBinarnogStabla levi;
	 private CvorBinarnogStabla desni;
	
	 public CvorBinarnogStabla(String vrednost,
			CvorBinarnogStabla levi, CvorBinarnogStabla desni) {
		
		this.vrednost = vrednost;
		this.levi = levi;
		this.desni = desni;
		kljuc=hashCode(vrednost);
	}

	public CvorBinarnogStabla() {
		
	}
	
	
	public static int hashCode(String vrednost) {
		int zbir=0;
		for (int i = 0; i < vrednost.length(); i++) {
			zbir+=vrednost.charAt(i)*Math.pow(10,vrednost.length()-i-1);
		}
		return zbir;
	}

	public int getKljuc() {
		return kljuc;
	}

	public String getVrednost() {
		return vrednost;
	}

	public void setVrednost(String vrednost) {
		this.vrednost = vrednost;
		kljuc=hashCode(vrednost);
	}

	public CvorBinarnogStabla getLevi() {
		return levi;
	}

	public void setLevi(CvorBinarnogStabla levi) {
		this.levi = levi;
	}

	public CvorBinarnogStabla getDesni() {
		return desni;
	}

	public void setDesni(CvorBinarnogStabla desni) {
		this.desni = desni;
	}


}
