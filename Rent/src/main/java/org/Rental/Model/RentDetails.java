package org.Rental.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class RentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    public RentDetails() {

    }

    public RentDetails(LocalDate sD, LocalDate eD) {
        this.startDate = sD;
        this.endDate = eD;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(Long id) { this.id = id; }

    public void setStartDate(LocalDate sD) {
        this.startDate = sD;
    }

    public void setEndDate(LocalDate eD) {
        this.endDate = eD;
    }


    public static class Builder {
        private Long id;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withStartDate(LocalDate sD) { this.startDate = sD; return this; }

        public Builder withEndDate(LocalDate eD) { this.endDate = eD; return this; }

        public RentDetails build() {
            RentDetails rentEntity = new RentDetails();

            rentEntity.setId(id);
            rentEntity.setStartDate(startDate);
            rentEntity.setEndDate(endDate);

            return rentEntity;
        }
    }
}
