package org.springframework.samples.petclinic.bill;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Visit;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bill")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "pet_id")
	private Integer petId;

	@Column(name = "paymentDate")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
}
