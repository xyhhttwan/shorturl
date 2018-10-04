package com.platform.soft.domain;

public class IdBuilder {

    /**
     * 步长
     */
    private int step;

    /**
     * 业务类型 1 是系统
     */
    private int biz;


    private Long id;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getBiz() {
        return biz;
    }

    public void setBiz(int biz) {
        this.biz = biz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
