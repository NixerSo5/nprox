package com.nixer.nprox.entity.swarm.pool;

import java.math.BigDecimal;

public class CashOutConfig {

    private BigDecimal min_bzz;

    private BigDecimal cashout_limit;

    public BigDecimal getMin_bzz() {
        return min_bzz;
    }

    public void setMin_bzz(BigDecimal min_bzz) {
        this.min_bzz = min_bzz;
    }

    public BigDecimal getCashout_limit() {
        return cashout_limit;
    }

    public void setCashout_limit(BigDecimal cashout_limit) {
        this.cashout_limit = cashout_limit;
    }


    // {"min_bzz": "0.0001", "cashout_limit": "50"}
}
