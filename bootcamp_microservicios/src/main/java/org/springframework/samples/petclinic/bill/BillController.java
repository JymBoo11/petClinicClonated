package org.springframework.samples.petclinic.bill;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bills")
public class BillController {
	@Autowired
	private BillRepository billRepository;

	@GetMapping
	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bill> getBillById(@PathVariable(value = "id") Integer billId) {
		Optional<Bill> bill = billRepository.findById(billId);
		if (!bill.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(bill.get());
	}

	@PostMapping
	public Bill createBill(@RequestBody Bill bill) {
		return billRepository.save(bill);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Bill> updateBill(@PathVariable(value = "id") Integer billId,
										   @RequestBody Bill billDetails) {
		Optional<Bill> bill = billRepository.findById(billId);
		if (!bill.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Bill billToUpdate = bill.get();
		billToUpdate.setPetId(billDetails.getPetId());
		billToUpdate.setPaymentDate(billDetails.getPaymentDate());
		billToUpdate.setOwner(billDetails.getOwner());
		billToUpdate.setVisit(billDetails.getVisit());

		Bill updatedBill = billRepository.save(billToUpdate);
		return ResponseEntity.ok(updatedBill);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBill(@PathVariable(value = "id") Integer billId) {
		Optional<Bill> bill = billRepository.findById(billId);
		if (!bill.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		billRepository.delete(bill.get());
		return ResponseEntity.noContent().build();
	}
}
