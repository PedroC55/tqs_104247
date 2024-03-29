package uni.tqs.cars_service.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    private Long carId;
    
    private String maker;
    private String model;

    public Car(){}

    public Car(String maker, String model){
        this.maker = maker;
        this.model = model;
    }

    public Long getId() { return this.carId; }

    public void setCarId(Long Id) { this.carId = Id; }

    public String getMaker() { return this.maker; }

    public void setMaker(String maker) { this.maker = maker; }

    public String getModel() { return this.model; }

    public void setModel(String model) { this.model = model; }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) && Objects.equals(maker, car.maker) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() { return Objects.hash(carId, maker, model); }


    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", maker='" + getMaker() + "'" +
            ", model='" + getModel() + "'" +
            "}";
    }


}