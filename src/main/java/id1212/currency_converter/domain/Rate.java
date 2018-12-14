package id1212.currency_converter.domain;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_RATE")
public class Rate implements RateDTO {
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "RATE_SEQUENCE")
    @Column(name = "EX_ID")
    private long exId;

    @Column(name = "EX_FROM")
    private String exFrom;

    @Column(name = "EX_TO")
    private String exTo;

    @Column(name = "EX_RATE")
    private double exRate;

    public Rate(String from, String to, double rate) {
        this.exFrom = from;
        this.exTo = to;
        this.exRate = rate;
    }

    // required by JPA
    protected Rate() {}


    public String getExFrom() {
        return exFrom;
    }

    public void setExFrom(String exFrom) {
        this.exFrom = exFrom;
    }

    public String getExTo() {
        return exTo;
    }

    public void setExTo(String exTo) {
        this.exTo = exTo;
    }

    public double getExRate() {
        return exRate;
    }

    public void setExRate(double exRate) {
        this.exRate = exRate;
    }

    @Override
    public double findRate(String exFrom, String exTo) {
        return exRate;
    }
}
