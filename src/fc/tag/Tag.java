package fc.tag;
/**
 *Classe abstraite permettant de transformer les descripteurs en
 *tout genre en des objets comparable les uns avec les autres 
 *
 * @author yazid
 *
 */
public abstract class Tag {
	private String tag;
	private Priority prio;
	/**
	 * Un tag ne peut jamais être instancié avec 
	 * un constructeur vide, redefinir le type d'exception que l'on throw?
	 * @throws Exception
	 */
	public Tag() throws Exception {
		throw new Exception("Un Tag vide ne peut pas exister");
	}
	/**
	 * Constructeur de base d'un tag prenant un string en argument
	 * @param tag
	 */
	public Tag(String tag) {
		this.tag=tag;
	}
	/**
	 * Constructeur secondaire d'un Tag si l'on veut modifier exceptionnelement la priorité d'un tag malgré
	 * sa classe réel
	 * @param tag
	 * @param prio
	 */
	public Tag(String tag,Priority prio) {
		this(tag);
		this.prio=prio;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Priority getPrio() {
		return prio;
	}
	public void setPrio(Priority prio) {
		this.prio = prio;
	}
}
