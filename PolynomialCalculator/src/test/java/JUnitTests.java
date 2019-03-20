// Created by Farcas Alexandru
// Group 30221
// UTCN 2019
import model.Polynome;
import org.junit.Assert;
import org.junit.Test;

public class JUnitTests {

    Polynome firstPolynome = new Polynome("34x^4-56x^3+4x^0");
    Polynome secondPolynome = new Polynome("-3x^3+6x^1");

    public JUnitTests() throws Exception {
    }

    // se testeaza constructorul polinomial din string
    @Test
    public  void inputTest() throws Exception {
        try{
            Polynome p = new Polynome("x");
            Assert.assertFalse(false);
        }
        catch (Exception e){
            Assert.assertTrue(true);
        }

        try{
            Polynome p = new Polynome("1x");
            Assert.assertFalse(false);
        }
        catch (Exception e){
            Assert.assertTrue(true);
        }

        try{
            Polynome p = new Polynome("1x^2");
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.assertFalse(false);
        }

        try{
            Polynome p = new Polynome("-3x");
            Assert.assertFalse(false);
        }
        catch (Exception e){
            Assert.assertTrue(true);
        }

        try{
            Polynome p = new Polynome("-3x^7");
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.assertFalse(false);
        }

        try{
            Polynome p = new Polynome("-67x^7+56x^7");
            Assert.assertTrue(true);
        }
        catch (Exception e){
            Assert.assertFalse(false);
        }
    }

    // testez pe rand fiecare operatie
    @Test
    public void addTest() throws Exception {
        firstPolynome.addPolynome(secondPolynome);
        Assert.assertEquals("34x^4-59x^3+6x^1+4", firstPolynome.printPolynome());
    }

    @Test
    public void substractTest() throws Exception {
        firstPolynome.substractPolynome(secondPolynome);
        Assert.assertEquals("34x^4-53x^3-6x^1+4", firstPolynome.printPolynome());
    }

    @Test
    public void multiplyTest(){
        firstPolynome.multiplyPolynome(secondPolynome);
        Assert.assertEquals("-102x^7+168x^6+204x^5-336x^4-12x^3+24x^1", firstPolynome.printPolynome());
    }

    @Test
    public void divideTest() throws Exception {
        firstPolynome.dividePolynome(secondPolynome);
        Assert.assertEquals("-11.333333x^1+18.666666", firstPolynome.printPolynome());
    }

    @Test
    public void deriveTest(){
        firstPolynome.derivePolynome();
        Assert.assertEquals("136x^3-168x^2", firstPolynome.printPolynome());
    }

    @Test
    public void integrateTest(){
        firstPolynome.integratePolynome();
        Assert.assertEquals("6.8x^5-14x^4+4x^1", firstPolynome.printPolynome());
    }
}
