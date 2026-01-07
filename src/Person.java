import java.time.LocalDate;
import java.time.Period;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class Person {
	protected String id;
	protected String name;
	protected LocalDate birthDate;
	protected String email;
	
	public Person(String id, String name, LocalDate birthDate, String email) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		log.info("Person created: {}", name);
	}
	
	public Person() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public int getAge() {
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
	
	public abstract String introduce();
	
	public final void showDetails() {
		log.info("Showing details for: {}",name);
		System.out.println("=== 詳細情報 ===");
		System.out.println("ID: "+id);
		System.out.println("名前: "+name);
		System.out.println("年齢: "+getAge()+"歳");
		System.out.println("Email: "+email);
		System.out.println(introduce());
	}
	
	/**
	 * 人物の基本情報を短縮形式で表示
	 * Week 6レッスン4で追加：複合変更管理の実践用
	 */
	public String getShortInfo() {
	    return String.format("%s(%d歳)", getName(), getAge());
	}

	/**
	 * メールドメインを取得
	 * Week 6レッスン4で追加：複合変更管理の実践用
	 */
	public String getEmailDomain() {
	    if (getEmail() != null && getEmail().contains("@")) {
	        return getEmail().substring(getEmail().indexOf("@") + 1);
	    }
	    return "不明";
	}

}
