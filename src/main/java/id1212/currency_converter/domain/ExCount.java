package id1212.currency_converter.domain;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_NUM")
public class ExCount implements ExCountDTO {
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "COUNT_SEQUENCE")
    @Column(name = "EX_NUM")
    private long exNum;

    @Column(name = "EX_TRANS")
    private String exTrans;

    public long getExNum() {
        return exNum;
    }

    public void setExNum(long exNum) {
        this.exNum = exNum;
    }

    public String getExTrans() {
        return exTrans;
    }

    public void setExTrans(String exTrans) {
        this.exTrans = exTrans;
    }

    public ExCount(String exTrans) {
        this.exTrans = exTrans;
    }


    // required by JPA
    protected ExCount() {}

}
