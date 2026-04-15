package com.geely.lib.oneosapi.payment;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class OrderRenderParam implements Parcelable {
    public static final Parcelable.Creator<OrderRenderParam> CREATOR = new Parcelable.Creator<OrderRenderParam>() { // from class: com.geely.lib.oneosapi.payment.OrderRenderParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrderRenderParam createFromParcel(Parcel in) {
            return new OrderRenderParam(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OrderRenderParam[] newArray(int size) {
            return new OrderRenderParam[size];
        }
    };
    private BuyMemberPointsDeductionParam buyMemberPointsDeductionParam;
    private String divisionIds;
    private FullReductionCouponParam fullReductionCouponParam;
    private List<OrderLineList> orderLineList;
    private String orderSource;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OrderRenderParam() {
    }

    public OrderRenderParam(String orderSource, List<OrderLineList> orderLineList, String divisionIds, BuyMemberPointsDeductionParam buyMemberPointsDeductionParam, FullReductionCouponParam fullReductionCouponParam) {
        this.orderSource = orderSource;
        this.orderLineList = orderLineList;
        this.divisionIds = divisionIds;
        this.buyMemberPointsDeductionParam = buyMemberPointsDeductionParam;
        this.fullReductionCouponParam = fullReductionCouponParam;
    }

    protected OrderRenderParam(Parcel in) {
        this.orderSource = in.readString();
        this.orderLineList = in.createTypedArrayList(OrderLineList.CREATOR);
        this.divisionIds = in.readString();
        this.buyMemberPointsDeductionParam = (BuyMemberPointsDeductionParam) in.readParcelable(BuyMemberPointsDeductionParam.class.getClassLoader());
        this.fullReductionCouponParam = (FullReductionCouponParam) in.readParcelable(FullReductionCouponParam.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderSource);
        dest.writeTypedList(this.orderLineList);
        dest.writeString(this.divisionIds);
        dest.writeParcelable(this.buyMemberPointsDeductionParam, flags);
        dest.writeParcelable(this.fullReductionCouponParam, flags);
    }

    public String getOrderSource() {
        return this.orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public List<OrderLineList> getOrderLineList() {
        return this.orderLineList;
    }

    public void setOrderLineList(List<OrderLineList> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public String getDivisionIds() {
        return this.divisionIds;
    }

    public void setDivisionIds(String divisionIds) {
        this.divisionIds = divisionIds;
    }

    public BuyMemberPointsDeductionParam getBuyMemberPointsDeductionParam() {
        return this.buyMemberPointsDeductionParam;
    }

    public void setBuyMemberPointsDeductionParam(BuyMemberPointsDeductionParam buyMemberPointsDeductionParam) {
        this.buyMemberPointsDeductionParam = buyMemberPointsDeductionParam;
    }

    public FullReductionCouponParam getFullReductionCouponParam() {
        return this.fullReductionCouponParam;
    }

    public void setFullReductionCouponParam(FullReductionCouponParam fullReductionCouponParam) {
        this.fullReductionCouponParam = fullReductionCouponParam;
    }
}