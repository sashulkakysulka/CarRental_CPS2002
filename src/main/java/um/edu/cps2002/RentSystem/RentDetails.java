package um.edu.cps2002.RentSystem;

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
    private char vehicle;
    private LocalDate startDate;
    private LocalDate endDate;

    public RentDetails() {

    }

    public RentDetails(char v, LocalDate sD, LocalDate eD) {
        this.vehicle = v;
        this.startDate = sD;
        this.endDate = eD;
    }

    public Long getId() {
        return id;
    }

    public char getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setId(Long id) { this.id = id; }

    public void setVehicle(char v) {
        this.vehicle = v;
    }

    public void setStartDate(LocalDate sD) {
        this.startDate = sD;
    }

    public void setEndDate(LocalDate eD) {
        this.endDate = eD;
    }


    public static class Builder {
        private Long id;
        private char vehicle;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withVehicle(char v) {
            this.vehicle = v;
            return this;
        }

        public Builder withStartDate(LocalDate sD) {
            this.startDate = sD;
            return this;
        }

        public Builder withEndDate(LocalDate eD) {
            this.endDate = eD;
            return this;
        }

        public RentDetails build() {
            RentDetails rentEntity = new RentDetails();

            rentEntity.setId(id);
            rentEntity.setVehicle(vehicle);
            rentEntity.setStartDate(startDate);
            rentEntity.setEndDate(endDate);

            return rentEntity;
        }
    }
}
