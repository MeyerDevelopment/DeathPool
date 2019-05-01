package deathpool.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CompKey.class)
public class DeathPool {
	
	@Id
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	@Id
	@ManyToOne
	@JoinColumn(name="characterid")
	private Character character;
	private String status;
	
	public DeathPool(User user, Character character, String status) {
		this.user = user;
		this.character = character;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DeathPool [user=" + user + ", character=" + character + ", status=" + status + "]";
	}
	
}
