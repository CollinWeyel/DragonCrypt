import java.io.UnsupportedEncodingException;

public class DragonCrypt {
    public DragonCrypt(){}

    //ASCII 32 - 126

    //Decodes the source string with the given hops
    public String ceasarDec(String source, int hops){
        String end = "";

        for(char c:source.toCharArray()){
            int tmp = (int) c + hops;

            //If tmp is smaller than 32 (the lowest writable character), subtract from highest writable character
            if(tmp < 32){
                tmp = 127 - (32 - tmp);

            //If tmp is bigger than 126 (the highest writable character), add to lowest writable character
            }else if(tmp > 126){
                tmp = 31 + (tmp - 126);
            }
            end += (char) tmp;
        }
        return end;
    }

    //Encodes the source string with the given hops
    public String ceasarEnc(String source, int hops){
        return ceasarDec(source, -hops);
    }

    //Turns the string 13 chars in the alphabet
    public String ceasar13(String source){
        return ceasarEnc(source, 13);
    }

    //Tries to crack the source till it contains the given string, then returns the key
    public int ceasarCrack(String source, String contains) throws DragonCryptException{
        for(int i = 0; i < 95; i++){
            if(ceasarEnc(source, i).contains(contains)){
                return 1;
            }
        }
        throw new DragonCryptException();
    }

    //Encodes input via logical AND
    public byte[] logicalAND(String source, String key){
        int j = 0;
        byte[] so = toBinary(source);
        byte[] ke = toBinary(key);
        byte[] ou = new byte[so.length];
        for(int i = 0; i < source.length(); i++){
            if(j == key.length()){
                j = 0;
            }
            ou[i] =(byte) (so[i] & ke[j]);
            j++;
        }
        return ou;
    }

    //Encodes input via logical OR
    public byte[] logicalOR(String source, String key){
        int j = 0;
        byte[] so = toBinary(source);
        byte[] ke = toBinary(key);
        byte[] ou = new byte[so.length];
        for(int i = 0; i < source.length(); i++){
            if(j == key.length()){
                j = 0;
            }
            ou[i] =(byte) (so[i] | ke[j]);
            j++;
        }
        return ou;
    }

    //Encodes input via logical XOR
    public byte[] logicalXOR(String source, String key){
        int j = 0;
        byte[] so = toBinary(source);
        byte[] ke = toBinary(key);
        byte[] ou = new byte[so.length];
        for(int i = 0; i < source.length(); i++){
            if(j == key.length()){
                j = 0;
            }
            ou[i] =(byte) (so[i] ^ ke[j]);
            j++;
        }
        return ou;
    }

    //Converts an int to binary
    public String toBinary(int source){
        return Integer.toBinaryString(source);
    }

    //Converts an String to binary
    public byte[] toBinary(String source){
        byte[] bytes = new byte[source.length()];

        for(int i = 0; i > bytes.length; i++){
            bytes[i] = (byte) source.charAt(i);
        }
        try {
            return source.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Converts an int to hex
    public String toHex(int source){
        return Integer.toHexString(source);
    }

    //Converts an String to hex
    public String toHex(String source){
        String end = "";

        for(char c:source.toCharArray()){
            end += Integer.toHexString((int) c);
        }
        return end;
    }

    //Converts an int to octal
    public String toOctal(int source){
        return Integer.toOctalString(source);
    }

    //Converts an String to octal
    public String toOctal(String source){
        String end = "";

        for(char c:source.toCharArray()){
            end += Integer.toOctalString((int) c);
        }
        return end;
    }

    //---------------------------------Presentation-----------------------------------------------
    //Moves bite[] to string
    public String byteArrayToString(byte[] bytes){
        String end = "";

        for(byte b:bytes){
            end += Integer.toBinaryString(b) + " ";
        }
        return end.trim();
    }

    //---------------------------------Exceptions-------------------------------------------------
    class DragonCryptException extends Exception {
        public DragonCryptException(){
            super("Error, provided content not found");
        }
    }
}
