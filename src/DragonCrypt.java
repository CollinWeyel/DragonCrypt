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

    //---------------------------------Exceptions-------------------------------------------------
    class DragonCryptException extends Exception {
        public DragonCryptException(){
            super("Error, provided content not found");
        }
    }
}
