package dev.ouhteen.lakeSide_hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name = "check_In")
    private LocalDateTime checkInTime;

    @Column(name = "check_Out")
    private LocalDateTime checkOutTime;

    @Column(name = "guest_Full_Name")
    private String guestFullName;

    @Column(name = "guest_Email")
    private String guestEmail;

    @Column(name = "adults")
    private int totalNumOfAdults;

    @Column(name = "children")
    private int totalNumOfChildren;

    @Column(name = "total_Guests")
    private int totalNumOfGuests;

    @Column(name = "confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumOfGuests(){
        this.totalNumOfGuests = this.totalNumOfAdults + totalNumOfChildren;
    }

    public void setTotalNumOfChildren(int totalNumOfChildren) {
        this.totalNumOfChildren = totalNumOfChildren;
        calculateTotalNumOfGuests();
    }

    public void setTotalNumOfAdults(int totalNumOfAdults) {
        this.totalNumOfAdults = totalNumOfAdults;
        calculateTotalNumOfGuests();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
