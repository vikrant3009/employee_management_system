class P1 {
public static void main(String args[]) {
try{
try{int ab = Integer.parseInt("Hello");}
catch(NumberFormatException nfe){System.out.println("Issue1nfe = " + nfe);}
}
catch(Exception e){System.out.println("Issue2e = " + e);}
}
}