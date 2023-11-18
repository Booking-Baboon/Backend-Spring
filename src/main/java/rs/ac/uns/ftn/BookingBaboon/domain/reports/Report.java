package rs.ac.uns.ftn.BookingBaboon.domain.reports;

import jakarta.persistence.*;
import lombok.Data;
import rs.ac.uns.ftn.BookingBaboon.domain.Users.User;

import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@Table(name = "reports")
@TableGenerator(name="report_id_generator", table="primary_keys", pkColumnName="key_pk", pkColumnValue="report", valueColumnName="value_pk")

public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "report_id_generator")
    private Long id;

    @ManyToOne
    private User reportee;

    private Date createdOn;
    private ReportStatus status;
    private String message;
}