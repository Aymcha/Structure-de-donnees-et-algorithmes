package Letter;

import Point.Point2d;
import Shape.*;

public final class LetterFactory {
    final static Double maxHeight = 150.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfStripeThickness = stripeThickness / 2;
    final static Double coordonneeNulle = 0.0;
    final static Double moitie = 1.0 / 2;


    /** TODO
     * Create the letter T graphically
     * @return BaseShape containing the letter T
     */
    public static BaseShape create_T() {
        BaseShape letterT = new BaseShape();
        Rectangle rectangle1 = new Rectangle(stripeThickness * moitie, maxHeight);
        Rectangle rectangle2 = new Rectangle(maxWidth, stripeThickness * moitie);
        rectangle2.translate(new Point2d(coordonneeNulle, -(halfMaxHeight - halfStripeThickness * moitie)));
        letterT.add(rectangle1);
        letterT.add(rectangle2);
        return letterT;
    }

    /** TODO
     * Create the letter E graphically
     * @return BaseShape containing the letter E
     */
    public static BaseShape create_E() {
        BaseShape letterE = new BaseShape();
        Rectangle rectangle1 = new Rectangle(stripeThickness * moitie, maxHeight);
        Rectangle rectangle2 = new Rectangle(maxWidth, stripeThickness * moitie);
        Rectangle rectangle3 = new Rectangle(maxWidth, stripeThickness * moitie);
        Rectangle rectangle4 = new Rectangle(maxWidth, stripeThickness * moitie);
        rectangle2.translate(new Point2d(coordonneeNulle, -(halfMaxHeight - halfStripeThickness * moitie)));
        rectangle3.translate(new Point2d(coordonneeNulle, halfMaxHeight - halfStripeThickness * moitie));
        rectangle1.translate(new Point2d(-(halfMaxWidth - halfStripeThickness * moitie), coordonneeNulle));
        letterE.add(rectangle1);
        letterE.add(rectangle2);
        letterE.add(rectangle3);
        letterE.add(rectangle4);
        return letterE;
    }

    /** TODO
     * Create the letter O graphically
     * @return BaseShape containing the letter O
     */
    public static BaseShape create_O() {
        BaseShape letterO = new BaseShape();
        Ellipse ellipse1 = new Ellipse(maxWidth, maxHeight);
        Ellipse ellipse2 = new Ellipse(maxWidth - stripeThickness, maxHeight - stripeThickness);
        letterO.add(ellipse1);
        letterO.remove(ellipse2);
        return letterO;
    }

    /** TODO
     * Create the letter C graphically
     * @return BaseShape containing the letter C
     */
    public static BaseShape create_C() {
        BaseShape letterC = create_O().clone();
        Rectangle rectangle1 = new Rectangle(halfMaxWidth, halfMaxHeight + stripeThickness);
        rectangle1.translate(new Point2d(halfMaxWidth * moitie, coordonneeNulle));
        letterC.remove(rectangle1);
        return letterC;
    }

    /** TODO
     * Create the letter A graphically
     * @return BaseShape containing the letter A
     */
    public static BaseShape create_A() {
        BaseShape letterA = create_V().clone();
        letterA.rotate(Math.toRadians(180));
        Rectangle rectangle3 = new Rectangle(maxWidth - halfStripeThickness, halfStripeThickness);
        rectangle3.translate(new Point2d(coordonneeNulle, halfStripeThickness));
        letterA.add(rectangle3);
        return letterA;
    }

    /** TODO
     * Create the letter V graphically
     * @return BaseShape containing the letter V
     */
    public static BaseShape create_V() {
        BaseShape letterV = new BaseShape();
        Rectangle rectangle1 = new Rectangle(halfStripeThickness, maxHeight);
        Rectangle rectangle2 = new Rectangle(halfStripeThickness, maxHeight);
        rectangle1.rotate(Math.toRadians(15));
        rectangle2.rotate(Math.toRadians(-15));
        rectangle1.translate(new Point2d(halfMaxWidth * moitie + halfStripeThickness * moitie, coordonneeNulle));
        rectangle2.translate(new Point2d(-halfMaxWidth * moitie - halfStripeThickness * moitie, coordonneeNulle));
        letterV.add(rectangle1);
        letterV.add(rectangle2);
        return letterV;
    }

    /** TODO
     * Create the letter N graphically
     * @return BaseShape containing the letter N
     */
    public static BaseShape create_N() {
        BaseShape letterN = new BaseShape();
        Rectangle rectangle1 = new Rectangle(halfStripeThickness, maxHeight);
        Rectangle rectangle2 = new Rectangle(halfStripeThickness, maxHeight + halfStripeThickness * moitie);
        Rectangle rectangle3 = new Rectangle(halfStripeThickness, maxHeight);
        rectangle2.rotate(Math.toRadians(-20));
        rectangle1.translate(new Point2d(-(halfMaxWidth), coordonneeNulle));
        rectangle3.translate(new Point2d(halfMaxWidth, coordonneeNulle));
        letterN.add(rectangle1);
        letterN.add(rectangle2);
        letterN.add(rectangle3);
        return letterN;
    }

    /** TODO
     * Create the letter M graphically
     * @return BaseShape containing the letter M
     */
    public static BaseShape create_M() {
        BaseShape letterM = new BaseShape();
        Rectangle rectangle1 = new Rectangle(halfStripeThickness, maxHeight);
        Rectangle rectangle2 = new Rectangle(halfStripeThickness, halfMaxHeight + halfStripeThickness * moitie);
        Rectangle rectangle3 = new Rectangle(halfStripeThickness, maxHeight);
        Rectangle rectangle4 = new Rectangle(halfStripeThickness, halfMaxHeight + halfStripeThickness * moitie);
        rectangle2.rotate(Math.toRadians(-20));
        rectangle4.rotate(Math.toRadians(20));
        rectangle1.translate(new Point2d(-(halfMaxWidth), coordonneeNulle));
        rectangle3.translate(new Point2d(halfMaxWidth, coordonneeNulle));
        rectangle2.translate(new Point2d(-stripeThickness + (halfStripeThickness / 3),
                -halfMaxHeight * moitie + halfStripeThickness * moitie));
        rectangle4.translate(new Point2d(stripeThickness - (halfStripeThickness / 3),
                -halfMaxHeight * moitie + halfStripeThickness * moitie));
        letterM.add(rectangle1);
        letterM.add(rectangle2);
        letterM.add(rectangle3);
        letterM.add(rectangle4);
        return letterM;
    }

}
