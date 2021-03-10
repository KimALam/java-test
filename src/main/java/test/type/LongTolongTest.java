package test.type;

import java.math.BigDecimal;

class LongTolongTest {
    public static void main(String[] args) {
        int rate = getDiscountRate(55000L, 100L);
        System.out.println("discount : " + rate);

        Boolean yn = true;
        System.out.println("boolean : " + yn.toString().toUpperCase());
    }

    public static int getDiscountRate(long selPrc, long finalDscPrc) {
        int discountRate = 0;

        BigDecimal dSellPrice = new BigDecimal(Long.toString(selPrc));
        BigDecimal dFinalPrice = new BigDecimal(Long.toString(finalDscPrc));

        try {
            if (dSellPrice.compareTo(dFinalPrice) == 1) {
                BigDecimal dscRate = (dSellPrice.subtract(dFinalPrice)).divide(dSellPrice, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal("100"));
                dscRate = dscRate.setScale(0); // 소숫점 없는 포멧
                discountRate = dscRate.intValue();
            }
        } catch (Exception e){
            //e.printStackTrace();
        }

        return discountRate;
    }
}
