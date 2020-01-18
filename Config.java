public class Config
{
    final static int MIN_CHAR = 33;  //Minimum char value used for password generation
    final static int MAX_CHAR = 126; //Maximum char value used for password generation

    final static int RULES_LEN = 4; //Length of the rules array
    final static int HAS_UC = 0;    //Index of upper case rule
    final static int HAS_LC = 1;    //Index of lower case rule
    final static int HAS_DIGIT = 2; //Index of digit rule
    final static int HAS_PUNCT = 3; //Index of has punctuation rule

    final static long SEED = 12344321; //Random generator seed
}
