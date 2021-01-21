package inlupg;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Del1 {

    public static void main(String[] args) {

	String[] austin = getArray();

	Stream<String> myStream = Arrays.stream(austin);
	runTaskOne(myStream);

	myStream = Arrays.stream(austin);
	runTaskTwo(myStream);

	myStream = Arrays.stream(austin);
	runTaskThree(myStream);

	myStream = Arrays.stream(austin);
	runTaskFour(myStream);

	myStream = Arrays.stream(austin);
	runTaskFive(myStream);

	myStream = Arrays.stream(austin);
	runTaskSix(myStream);

	Predicate<String> predA = x -> x.length() < 12;
	Predicate<String> predB = x -> x.length() > 2;

	myStream = Arrays.stream(austin);
	runTaskSevenA(myStream, predA);

	myStream = Arrays.stream(austin);
	runTaskSevenB(myStream, predB);

    }

    public static void runTaskOne(Stream<String> myStream) {
	long taskOne = myStream
	        .filter(x -> x.length() > 8)
	        .count();

	System.out.println("1.1) Number of words longer then 8: " + taskOne);
    }

    public static void runTaskTwo(Stream<String> myStream) {
	long taskTwo = myStream
	        .distinct()
	        .count();

	System.out.println("1.2) Number of unique words: " + taskTwo);
    }

    public static void runTaskThree(Stream<String> myStream) {
	long taskThree = myStream
	        .filter(x -> x.length() < 4)
	        .count();

	System.out.println("1.3) Number of words shorter then 4: " + taskThree);
    }

    public static void runTaskFour(Stream<String> myStream) {
	long taskFour = myStream
	        .distinct()
	        .filter(x -> x.length() > 8)
	        .count();

	System.out.println("1.4) Number of unique words longer then 8: " + taskFour);
    }

    public static void runTaskFive(Stream<String> myStream) {
	OptionalDouble taskFive = myStream
	        .mapToInt(x -> x.length())
	        .average();

	System.out.println("1.5) Avarage word length: " + taskFive.getAsDouble());
    }

    public static void runTaskSix(Stream<String> myStream) {
	int taskSix = myStream
	        .mapToInt(x -> x.length())
	        .sum();

	System.out.println("1.6) Total number of characters: " + taskSix);
    }

    public static void runTaskSevenA(Stream<String> myStream, Predicate<String> predA) {
	Boolean taskSevenA = myStream
	        .allMatch(predA);

	System.out.println("1.7A) All words are shorter then 12 characters: " + taskSevenA);
    }

    public static void runTaskSevenB(Stream<String> myStream, Predicate<String> predB) {
	Boolean taskSevenB = myStream
	        .allMatch(predB);

	System.out.println("1.7B) All words are longer then 2 characters: " + taskSevenB);
    }

    public static String[] getArray() {
	String[] austin = { "pride", "and", "prejudice", "by", "jane", "austen", "chapter", "it", "is", "a", "truth",
	        "universally", "acknowledged", "that", "a", "single", "man", "in", "possession", "of", "a", "good",
	        "fortune", "must", "be", "in", "want", "of", "a", "wife", "however", "little", "known", "the",
	        "feelings", "or", "views", "of", "such", "a", "man", "may", "be", "on", "his", "first", "entering", "a",
	        "neighbourhood", "this", "truth", "is", "so", "well", "fixed", "in", "the", "minds", "of", "the",
	        "surrounding", "families", "that", "he", "is", "considered", "the", "rightful", "property", "of",
	        "some", "one", "or", "other", "of", "their", "daughters", "my", "dear", "mr", "bennet", "said", "his",
	        "lady", "to", "him", "one", "day", "have", "you", "heard", "that", "netherfield", "park", "is", "let",
	        "at", "last", "mr", "bennet", "replied", "that", "he", "had", "not", "but", "it", "is", "returned",
	        "she", "for", "mrs", "long", "has", "just", "been", "here", "and", "she", "told", "me", "all", "about",
	        "it", "mr", "bennet", "made", "no", "answer", "do", "you", "not", "want", "to", "know", "who", "has",
	        "taken", "it", "cried", "his", "wife", "impatiently", "you", "want", "to", "tell", "me", "and", "i",
	        "have", "no", "objection", "to", "hearing", "it", "this", "was", "invitation", "enough", "why", "my",
	        "dear", "you", "must", "know", "mrs", "long", "says", "that", "netherfield", "is", "taken", "by", "a",
	        "young", "man", "of", "large", "fortune", "from", "the", "north", "of", "england", "that", "he", "came",
	        "down", "on", "monday", "in", "a", "chaise", "and", "four", "to", "see", "the", "place", "and", "was",
	        "so", "much", "delighted", "with", "it", "that", "he", "agreed", "with", "mr", "morris", "immediately",
	        "that", "he", "is", "to", "take", "possession", "before", "michaelmas", "and", "some", "of", "his",
	        "servants", "are", "to", "be", "in", "the", "house", "by", "the", "end", "of", "next", "week", "what",
	        "is", "his", "name", "bingley", "is", "he", "married", "or", "single", "oh", "single", "my", "dear",
	        "to", "be", "sure", "a", "single", "man", "of", "large", "fortune", "four", "or", "five", "thousand",
	        "a", "year", "what", "a", "fine", "thing", "for", "our", "girls", "how", "so", "how", "can", "it",
	        "affect", "them", "my", "dear", "mr", "bennet", "replied", "his", "wife", "how", "can", "you", "be",
	        "so", "tiresome", "you", "must", "know", "that", "i", "am", "thinking", "of", "his", "marrying", "one",
	        "of", "them", "is", "that", "his", "design", "in", "settling", "here", "design", "nonsense", "how",
	        "can", "you", "talk", "so", "but", "it", "is", "very", "likely", "that", "he", "may", "fall", "in",
	        "love", "with", "one", "of", "them", "and", "therefore", "you", "must", "visit", "him", "as", "soon",
	        "as", "he", "comes", "i", "see", "no", "occasion", "for", "that", "you", "and", "the", "girls", "may",
	        "go", "or", "you", "may", "send", "them", "by", "themselves", "which", "perhaps", "will", "be", "still",
	        "better", "for", "as", "you", "are", "as", "handsome", "as", "any", "of", "them", "mr", "bingley",
	        "may", "like", "you", "the", "best", "of", "the", "party", "my", "dear", "you", "flatter", "me", "i",
	        "certainly", "have", "had", "my", "share", "of", "beauty", "but", "i", "do", "not", "pretend", "to",
	        "be", "anything", "extraordinary", "now", "when", "a", "woman", "has", "five", "grown", "up",
	        "daughters", "she", "ought", "to", "give", "over", "thinking", "of", "her", "own", "beauty", "in",
	        "such", "cases", "a", "woman", "has", "not", "often", "much", "beauty", "to", "think", "of", "but",
	        "my", "dear", "you", "must", "indeed", "go", "and", "see", "mr", "bingley", "when", "he", "comes",
	        "into", "the", "neighbourhood", "it", "is", "more", "than", "i", "engage", "for", "i", "assure", "you",
	        "but", "consider", "your", "daughters", "only", "think", "what", "an", "establishment", "it", "would",
	        "be", "for", "one", "of", "them", "sir", "william", "and", "lady", "lucas", "are", "determined", "to",
	        "go", "merely", "on", "that", "account", "for", "in", "general", "you", "know", "they", "visit", "no",
	        "newcomers", "indeed", "you", "must", "go", "for", "it", "will", "be", "impossible", "for", "us", "to",
	        "visit", "him", "if", "you", "do", "not", "you", "are", "over", "scrupulous", "surely", "i", "dare",
	        "say", "mr", "bingley", "will", "be", "very", "glad", "to", "see", "you", "and", "i", "will", "send",
	        "a", "few", "lines", "by", "you", "to", "assure", "him", "of", "my", "hearty", "consent", "to", "his",
	        "marrying", "whichever", "he", "chooses", "of", "the", "girls", "though", "i", "must", "throw", "in",
	        "a", "good", "word", "for", "my", "little", "lizzy", "i", "desire", "you", "will", "do", "no", "such",
	        "thing", "lizzy", "is", "not", "a", "bit", "better", "than", "the", "others", "and", "i", "am", "sure",
	        "she", "is", "not", "half", "so", "handsome", "as", "jane", "nor", "half", "so", "good", "humoured",
	        "as", "lydia", "but", "you", "are", "always", "giving", "her", "the", "preference", "they", "have",
	        "none", "of", "them", "much", "to", "recommend", "them", "replied", "he", "they", "are", "all", "silly",
	        "and", "ignorant", "like", "other", "girls", "but", "lizzy", "has", "something", "more", "of",
	        "quickness", "than", "her", "sisters", "mr", "bennet", "how", "can", "you", "abuse", "your", "own",
	        "children", "in", "such", "a", "way", "you", "take", "delight", "in", "vexing", "me", "you", "have",
	        "no", "compassion", "for", "my", "poor", "nerves", "you", "mistake", "me", "my", "dear", "i", "have",
	        "a", "high", "respect", "for", "your", "nerves", "they", "are", "my", "old", "friends", "i", "have",
	        "heard", "you", "mention", "them", "with", "consideration", "these", "last", "twenty", "years", "at",
	        "least", "ah", "you", "do", "not", "know", "what", "i", "suffer", "but", "i", "hope", "you", "will",
	        "get", "over", "it", "and", "live", "to", "see", "many", "young", "men", "of", "four", "thousand", "a",
	        "year", "come", "into", "the", "neighbourhood", "it", "will", "be", "no", "use", "to", "us", "if",
	        "twenty", "such", "should", "come", "since", "you", "will", "not", "visit", "them", "depend", "upon",
	        "it", "my", "dear", "that", "when", "there", "are", "twenty", "i", "will", "visit", "them", "all", "mr",
	        "bennet", "was", "so", "odd", "a", "mixture", "of", "quick", "parts", "sarcastic", "humour", "reserve",
	        "and", "caprice", "that", "the", "experience", "of", "three", "and", "twenty", "years", "had", "been",
	        "insufficient", "to", "make", "his", "wife", "understand", "his", "character", "her", "mind", "was",
	        "less", "difficult", "to", "develop", "she", "was", "a", "woman", "of", "mean", "understanding",
	        "little", "information", "and", "uncertain", "temper", "when", "she", "was", "discontented", "she",
	        "fancied", "herself", "nervous", "the", "business", "of", "her", "life", "was", "to", "get", "her",
	        "daughters", "married", "its", "solace", "was", "visiting", "and", "news", "chapter", "mr", "bennet",
	        "was", "among", "the", "earliest", "of", "those", "who", "waited", "on", "mr", "bingley", "he", "had",
	        "always", "intended", "to", "visit", "him", "though", "to", "the", "last", "always", "assuring", "his",
	        "wife", "that", "he", "should", "not", "go", "and", "till", "the", "evening", "after", "the", "visit",
	        "was", "paid", "she", "had", "no", "knowledge", "of", "it", "it", "was", "then", "disclosed", "in",
	        "the", "following", "manner", "observing", "his", "second", "daughter", "employed", "in", "trimming",
	        "a", "hat", "he", "suddenly", "addressed", "her", "with", "i", "hope", "mr", "bingley", "will", "like",
	        "it", "lizzy", "we", "are", "not", "in", "a", "way", "to", "know", "what", "mr", "bingley", "likes",
	        "said", "her", "mother", "resentfully", "since", "we", "are", "not", "to", "visit", "but", "you",
	        "forget", "mamma", "said", "elizabeth", "that", "we", "shall", "meet", "him", "at", "the", "assemblies",
	        "and", "that", "mrs", "long", "promised", "to", "introduce", "him", "i", "do", "not", "believe", "mrs",
	        "long", "will", "do", "any", "such", "thing", "she", "has", "two", "nieces", "of", "her", "own", "she",
	        "is", "a", "selfish", "hypocritical", "woman", "and", "i", "have", "no", "opinion", "of", "her", "no",
	        "more", "have", "i", "said", "mr", "bennet", "and", "i", "am", "glad", "to", "find", "that", "you",
	        "do", "not", "depend", "on", "her", "serving", "you", "mrs", "bennet", "deigned", "not", "to", "make",
	        "any", "reply", "but", "unable", "to", "contain", "herself", "began", "scolding", "one", "of", "her",
	        "daughters", "dont", "keep", "coughing", "so", "kitty", "for", "heavens", "sake", "have", "a", "little",
	        "compassion", "on", "my", "nerves", "you", "tear", "them", "to", "pieces", "kitty", "has", "no",
	        "discretion", "in", "her", "coughs", "said", "her", "father", "she", "times", "them", "ill", "i", "do",
	        "not", "cough", "for", "my", "own", "amusement", "replied", "kitty", "fretfully", "when", "is", "your",
	        "next", "ball", "to", "be", "lizzy", "to", "morrow", "fortnight", "aye", "so", "it", "is", "cried",
	        "her", "mother", "and", "mrs", "long", "does", "not", "come", "back", "till", "the", "day", "before",
	        "so", "it", "will", "be", "impossible", "for", "her", "to", "introduce", "him", "for", "she", "will",
	        "not", "know", "him", "herself", "then", "my", "dear", "you", "may", "have", "the", "advantage", "of",
	        "your", "friend", "and", "introduce", "mr", "bingley", "to", "her", "impossible", "mr", "bennet",
	        "impossible", "when", "i", "am", "not", "acquainted", "with", "him", "myself", "how", "can", "you",
	        "be", "so", "teasing", "i", "honour", "your", "circumspection", "a", "fortnights", "acquaintance", "is",
	        "certainly", "very", "little", "one", "cannot", "know", "what", "a", "man", "really", "is", "by", "the",
	        "end", "of", "a", "fortnight", "but", "if", "we", "do", "not", "venture", "somebody", "else", "will",
	        "and", "after", "all", "mrs", "long", "and", "her", "neices", "must", "stand", "their", "chance", "and",
	        "therefore", "as", "she", "will", "think", "it", "an", "act", "of", "kindness", "if", "you", "decline",
	        "the", "office", "i", "will", "take", "it", "on", "myself", "the", "girls", "stared", "at", "their",
	        "father", "mrs", "bennet", "said", "only", "nonsense", "nonsense", "what", "can", "be", "the",
	        "meaning", "of", "that", "emphatic", "exclamation", "cried", "he", "do", "you", "consider", "the",
	        "forms", "of", "introduction", "and", "the", "stress", "that", "is", "laid", "on", "them", "as",
	        "nonsense", "i", "cannot", "quite", "agree", "with", "you", "there", "what", "say", "you", "mary",
	        "for", "you", "are", "a", "young", "lady", "of", "deep", "reflection", "i", "know", "and", "read",
	        "great", "books", "and", "make", "extracts", "mary", "wished", "to", "say", "something", "sensible",
	        "but", "knew", "not", "how", "while", "mary", "is", "adjusting", "her", "ideas", "he", "continued",
	        "let", "us", "return", "to", "mr", "bingley", "i", "am", "sick", "of", "mr", "bingley", "cried", "his",
	        "wife", "i", "am", "sorry", "to", "hear", "that", "but", "why", "did", "not", "you", "tell", "me",
	        "that", "before", "if", "i", "had", "known", "as", "much", "this", "morning", "i", "certainly", "would",
	        "not", "have", "called", "on", "him", "it", "is", "very", "unlucky", "but", "as", "i", "have",
	        "actually", "paid", "the", "visit", "we", "cannot", "escape", "the", "acquaintance", "now", "the",
	        "astonishment", "of", "the", "ladies", "was", "just", "what", "he", "wished", "that", "of", "mrs",
	        "bennet", "perhaps", "surpassing", "the", "rest", "though", "when", "the", "first", "tumult", "of",
	        "joy", "was", "over", "she", "began", "to", "declare", "that", "it", "was", "what", "she", "had",
	        "expected", "all", "the", "while", "how", "good", "it", "was", "in", "you", "my", "dear", "mr",
	        "bennet", "but", "i", "knew", "i", "should", "persuade", "you", "at", "last", "i", "was", "sure", "you",
	        "loved", "your", "girls", "too", "well", "to", "neglect", "such", "an", "acquaintance", "well", "how",
	        "pleased", "i", "am", "and", "it", "is", "such", "a", "good", "joke", "too", "that", "you", "should",
	        "have", "gone", "this", "morning", "and", "never", "said", "a", "word", "about", "it", "till", "now",
	        "now", "kitty", "you", "may", "cough", "as", "much", "as", "you", "choose", "said", "mr", "bennet",
	        "and", "as", "he", "spoke", "he", "left", "the", "room", "fatigued", "with", "the", "raptures", "of",
	        "his", "wife", "what", "an", "excellent", "father", "you", "have", "girls", "said", "she", "when",
	        "the", "door", "was", "shut", "i", "do", "not", "know", "how", "you", "will", "ever", "make", "him",
	        "amends", "for", "his", "kindness", "or", "me", "either", "for", "that", "matter", "at", "our", "time",
	        "of", "life", "it", "is", "not", "so", "pleasant", "i", "can", "tell", "you", "to", "be", "making",
	        "new", "acquaintances", "every", "day", "but", "for", "your", "sakes", "we", "would", "do", "anything",
	        "lydia", "my", "love", "though", "you", "are", "the", "youngest", "i", "dare", "say", "mr", "bingley",
	        "will", "dance", "with", "you", "at", "the", "next", "ball", "oh", "said", "lydia", "stoutly", "i",
	        "am", "not", "afraid", "for", "though", "i", "am", "the", "youngest", "im", "the", "tallest", "the",
	        "rest", "of", "the", "evening", "was", "spent", "in", "conjecturing", "how", "soon", "he", "would",
	        "return", "mr", "bennets", "visit", "and", "determining", "when", "they", "should", "ask", "him", "to",
	        "dinner", "chapter", "not", "all", "that", "mrs", "bennet", "however", "with", "the", "assistance",
	        "of", "her", "five", "daughters", "could", "ask", "on", "the", "subject", "was", "sufficient", "to",
	        "draw", "from", "her", "husband", "any", "satisfactory", "description", "of", "mr", "bingley", "they",
	        "attacked", "him", "in", "various", "ways", "with", "barefaced", "questions", "ingenious",
	        "suppositions", "and", "distant", "surmises", "but", "he", "eluded", "the", "skill", "of", "them",
	        "all", "and", "they", "were", "at", "last", "obliged", "to", "accept", "the", "second", "hand",
	        "intelligence", "of", "their", "neighbour", "lady", "lucas", "her", "report", "was", "highly",
	        "favourable", "sir", "william", "had", "been", "delighted", "with", "him", "he", "was", "quite",
	        "young", "wonderfully", "handsome", "extremely", "agreeable", "and", "to", "crown", "the", "whole",
	        "he", "meant", "to", "be", "at", "the", "next", "assembly", "with", "a", "large", "party", "nothing",
	        "could", "be", "more", "delightful", "to", "be", "fond", "of", "dancing", "was", "a", "certain", "step",
	        "towards", "falling", "in", "love", "and", "very", "lively", "hopes", "of", "mr", "bingleys", "heart",
	        "were", "entertained", "if", "i", "can", "but", "see", "one", "of", "my", "daughters", "happily",
	        "settled", "at", "netherfield", "said", "mrs", "bennet", "to", "her", "husband", "and", "all", "the",
	        "others", "equally", "well", "married", "i", "shall", "have", "nothing", "to", "wish", "for", "in", "a",
	        "few", "days", "mr", "bingley", "returned", "mr", "bennets", "visit", "and", "sat", "about", "ten",
	        "minutes", "with", "him", "in", "his", "library", "he", "had", "entertained", "hopes", "of", "being",
	        "admitted", "to", "a", "sight", "of", "the", "young", "ladies", "of", "whose", "beauty", "he", "had",
	        "heard", "much", "but", "he", "saw", "only", "the", "father", "the", "ladies", "were", "somewhat",
	        "more", "fortunate", "for", "they", "had", "the", "advantage", "of", "ascertaining", "from", "an",
	        "upper", "window", "that", "he", "wore", "a", "blue", "coat", "and", "rode", "a", "black", "horse",
	        "an", "invitation", "to", "dinner", "was", "soon", "afterwards", "dispatched", "and", "already", "had",
	        "mrs", "bennet", "planned", "the", "courses", "that", "were", "to", "do", "credit", "to", "her",
	        "housekeeping", "when", "an", "answer", "arrived", "which", "deferred", "it", "all", "mr", "bingley",
	        "was", "obliged", "to", "be", "in", "town", "the", "following", "day", "and", "consequently", "unable",
	        "to", "accept", "the", "honour", "of", "their", "invitation", "etc", "mrs", "bennet", "was", "quite",
	        "disconcerted", "she", "could", "not", "imagine", "what", "business", "he", "could", "have", "in",
	        "town", "so", "soon", "after", "his", "arrival", "in", "hertfordshire", "and", "she", "began", "to",
	        "fear", "that", "he", "might", "be", "always", "flying", "about", "from", "one", "place", "to",
	        "another", "and", "never", "settled", "at", "netherfield", "as", "he", "ought", "to", "be", "lady",
	        "lucas", "quieted", "her", "fears", "a", "little", "by", "starting", "the", "idea", "of", "his",
	        "being", "gone", "to", "london", "only", "to", "get", "a", "large", "party", "for", "the", "ball",
	        "and", "a", "report", "soon", "followed", "that", "mr", "bingley", "was", "to", "bring", "twelve",
	        "ladies", "and", "seven", "gentlemen", "with", "him", "to", "the", "assembly", "the", "girls",
	        "grieved", "over", "such", "a", "number", "of", "ladies", "but", "were", "comforted", "the", "day",
	        "before", "the", "ball", "by", "hearing", "that", "instead", "of", "twelve", "he", "brought", "only",
	        "six", "with", "him", "from", "london", "his", "five", "sisters", "and", "a", "cousin", "and", "when",
	        "the", "party", "entered", "the", "assembly", "room", "it", "consisted", "of", "only", "five",
	        "altogether", "mr", "bingley", "his", "two", "sisters", "the", "husband", "of", "the", "eldest", "and",
	        "another", "young", "man", "mr", "bingley", "was", "good", "looking", "and", "gentlemanlike", "he",
	        "had", "a", "pleasant", "countenance", "and", "easy", "unaffected", "manners", "his", "sisters", "were",
	        "fine", "women", "with", "an", "air", "of", "decided", "fashion", "his", "brother", "in", "law", "mr",
	        "hurst", "merely", "looked", "the", "gentleman", "but", "his", "friend", "mr", "darcy", "soon", "drew",
	        "the", "attention", "of", "the", "room", "by", "his", "fine", "tall", "person", "handsome", "features",
	        "noble", "mien", "and", "the", "report", "which", "was", "in", "general", "circulation", "within",
	        "five", "minutes", "after", "his", "entrance", "of", "his", "having", "ten", "thousand", "a", "year",
	        "the", "gentlemen", "pronounced", "him", "to", "be", "a", "fine", "figure", "of", "a", "man", "the",
	        "ladies", "declared", "he", "was", "much", "handsomer", "than", "mr", "bingley", "and", "he", "was",
	        "looked", "at", "with", "great", "admiration", "for", "about", "half", "the", "evening", "till", "his",
	        "manners", "gave", "a", "disgust", "which", "turned", "the", "tide", "of", "his", "popularity", "for",
	        "he", "was", "discovered", "to", "be", "proud", "to", "be", "above", "his", "company", "and", "above",
	        "being", "pleased", "and", "not", "all", "his", "large", "estate", "in", "derbyshire", "could", "then",
	        "save", "him", "from", "having", "a", "most", "forbidding", "disagreeable", "countenance", "and",
	        "being", "unworthy", "to", "be", "compared", "with", "his", "friend", "mr", "bingley", "had", "soon",
	        "made", "himself", "acquainted", "with", "all", "the", "principal", "people", "in", "the", "room", "he",
	        "was", "lively", "and", "unreserved", "danced", "every", "dance", "was", "angry", "that", "the", "ball",
	        "closed", "so", "early", "and", "talked", "of", "giving", "one", "himself", "at", "netherfield", "such",
	        "amiable", "qualities", "must", "speak", "for", "themselves", "what", "a", "contrast", "between", "him",
	        "and", "his", "friend", "mr", "darcy", "danced", "only", "once", "with", "mrs", "hurst", "and", "once",
	        "with", "miss", "bingley", "declined", "being", "introduced", "to", "any", "other", "lady", "and",
	        "spent", "the", "rest", "of", "the", "evening", "in", "walking", "about", "the", "room", "speaking",
	        "occasionally", "to", "one", "of", "his", "own", "party", "his", "character", "was", "decided", "he",
	        "was", "the", "proudest", "most", "disagreeable", "man", "in", "the", "world", "and", "everybody",
	        "hoped", "that", "he", "would", "never", "come", "there", "again", "amongst", "the", "most", "violent",
	        "against", "him", "was", "mrs", "bennet", "whose", "dislike", "of", "his", "general", "behaviour",
	        "was", "sharpened", "into", "particular", "resentment", "by", "his", "having", "slighted", "one", "of",
	        "her", "daughters", "elizabeth", "bennet", "had", "been", "obliged", "by", "the", "scarcity", "of",
	        "gentlemen", "to", "sit", "down", "for", "two", "dances", "and", "during", "part", "of", "that", "time",
	        "mr", "darcy", "had", "been", "standing", "near", "enough", "for", "her", "to", "hear", "a",
	        "conversation", "between", "him", "and", "mr", "bingley", "who", "came", "from", "the", "dance", "for",
	        "a", "few", "minutes", "to", "press", "his", "friend", "to", "join", "it", "come", "darcy", "said",
	        "he", "i", "must", "have", "you", "dance", "i", "hate", "to", "see", "you", "standing", "about", "by",
	        "yourself", "in", "this", "stupid", "manner", "you", "had", "much", "better", "dance", "i", "certainly",
	        "shall", "not", "you", "know", "how", "i", "detest", "it", "unless", "i", "am", "particularly",
	        "acquainted", "with", "my", "partner", "at", "such", "an", "assembly", "as", "this", "it", "would",
	        "be", "insupportable", "your", "sisters", "are", "engaged", "and", "there", "is", "not", "another",
	        "woman", "in", "the", "room", "whom", "it", "would", "not", "be", "a", "punishment", "to", "me", "to",
	        "stand", "up", "with", "i", "would", "not", "be", "so", "fastidious", "as", "you", "are", "cried", "mr",
	        "bingley", "for", "a", "kingdom", "upon", "my", "honour", "i", "never", "met", "with", "so", "many",
	        "pleasant", "girls", "in", "my", "life", "as", "i", "have", "this", "evening", "and", "there", "are",
	        "several", "of", "them", "you", "see", "uncommonly", "pretty", "you", "are", "dancing", "with", "the",
	        "only", "handsome", "girl", "in", "the", "room", "said", "mr", "darcy", "looking", "at", "the",
	        "eldest", "miss", "bennet", "oh", "she", "is", "the", "most", "beautiful", "creature", "i", "ever",
	        "beheld", "but", "there", "is", "one", "of", "her", "sisters", "sitting", "down", "just", "behind",
	        "you", "who", "is", "very", "pretty", "and", "i", "dare", "say", "very", "agreeable", "do", "let", "me",
	        "ask", "my", "partner", "to", "introduce", "you", "which", "do", "you", "mean", "and", "turning",
	        "round", "he", "looked", "for", "a", "moment", "at", "elizabeth", "till", "catching", "her", "eye",
	        "he", "withdrew", "his", "own", "and", "coldly", "said", "she", "is", "tolerable", "but", "not",
	        "handsome", "enough", "to", "tempt", "me", "i", "am", "in", "no", "humour", "at", "present", "to",
	        "give", "consequence", "to", "young", "ladies", "who", "are", "slighted", "by", "other", "men", "you",
	        "had", "better", "return", "to", "your", "partner", "and", "enjoy", "her", "smiles", "for", "you",
	        "are", "wasting", "your", "time", "with", "me", "mr", "bingley", "followed", "his", "advice", "mr",
	        "darcy", "walked", "off", "and", "elizabeth", "remained", "with", "no", "very", "cordial", "feelings",
	        "toward", "him", "she", "told", "the", "story", "however", "with", "great", "spirit", "among", "her",
	        "friends", "for", "she", "had", "a", "lively", "playful", "disposition", "which", "delighted", "in",
	        "anything", "ridiculous", "the", "evening", "altogether", "passed", "off", "pleasantly", "to", "the",
	        "whole", "family", "mrs", "bennet", "had", "seen", "her", "eldest", "daughter", "much", "admired", "by",
	        "the", "netherfield", "party", "mr", "bingley", "had", "danced", "with", "her", "twice", "and", "she",
	        "had", "been", "distinguished", "by", "his", "sisters", "jane", "was", "as", "much", "gratified", "by",
	        "this", "as", "her", "mother", "could", "be", "though", "in", "a", "quieter", "way", "elizabeth",
	        "felt", "janes", "pleasure", "mary", "had", "heard", "herself", "mentioned", "to", "miss", "bingley",
	        "as", "the", "most", "accomplished", "girl", "in", "the", "neighbourhood", "and", "catherine", "and",
	        "lydia", "had", "been", "fortunate", "enough", "never", "to", "be", "without", "partners", "which",
	        "was", "all", "that", "they", "had", "yet", "learnt", "to", "care", "for", "at", "a", "ball", "they",
	        "returned", "therefore", "in", "good", "spirits", "to", "longbourn", "the", "village", "where", "they",
	        "lived", "and", "of", "which", "they", "were", "the", "principal", "inhabitants", "they", "found", "mr",
	        "bennet", "still", "up", "with", "a", "book", "he", "was", "regardless", "of", "time", "and", "on",
	        "the", "present", "occasion", "he", "had", "a", "good", "deal", "of", "curiosity", "as", "to", "the",
	        "event", "of", "an", "evening", "which", "had", "raised", "such", "splendid", "expectations", "he",
	        "had", "rather", "hoped", "that", "his", "wifes", "views", "on", "the", "stranger", "would", "be",
	        "disappointed", "but", "he", "soon", "found", "out", "that", "he", "had", "a", "different", "story",
	        "to", "hear", "oh", "my", "dear", "mr", "bennet", "as", "she", "entered", "the", "room", "we", "have",
	        "had", "a", "most", "delightful", "evening", "a", "most", "excellent", "ball", "i", "wish", "you",
	        "had", "been", "there", "jane", "was", "so", "admired", "nothing", "could", "be", "like", "it",
	        "everybody", "said", "how", "well", "she", "looked", "and", "mr", "bingley", "thought", "her", "quite",
	        "beautiful", "and", "danced", "with", "her", "twice", "only", "think", "of", "that", "my", "dear", "he",
	        "actually", "danced", "with", "her", "twice", "and", "she", "was", "the", "only", "creature", "in",
	        "the", "room", "that", "he", "asked", "a", "second", "time", "first", "of", "all", "he", "asked",
	        "miss", "lucas", "i", "was", "so", "vexed", "to", "see", "him", "stand", "up", "with", "her", "but",
	        "however", "he", "did", "not", "admire", "her", "at", "all", "indeed", "nobody", "can", "you", "know",
	        "and", "he", "seemed", "quite", "struck", "with", "jane", "as", "she", "was", "going", "down", "the",
	        "dance", "so", "he", "inquired", "who", "she", "was", "and", "got", "introduced", "and", "asked", "her",
	        "for", "the", "two", "next", "then", "the", "two", "third", "he", "danced", "with", "miss", "king",
	        "and", "the", "two", "fourth", "with", "maria", "lucas", "and", "the", "two", "fifth", "with", "jane",
	        "again", "and", "the", "two", "sixth", "with", "lizzy", "and", "the", "boulanger", "if", "he", "had",
	        "had", "any", "compassion", "for", "me", "cried", "her", "husband", "impatiently", "he", "would", "not",
	        "have", "danced", "half", "so", "much", "for", "gods", "sake", "say", "no", "more", "of", "his",
	        "partners", "oh", "that", "he", "had", "sprained", "his", "ankle", "in", "the", "first", "dance", "oh",
	        "my", "dear", "i", "am", "quite", "delighted", "with", "him", "he", "is", "so", "excessively",
	        "handsome", "and", "his", "sisters", "are", "charming", "women", "i", "never", "in", "my", "life",
	        "saw", "anything", "more", "elegant", "than", "their", "dresses", "i", "dare", "say", "the", "lace",
	        "upon", "mrs", "hursts", "gown", "here", "she", "was", "interrupted", "again", "mr", "bennet",
	        "protested", "against", "any", "description", "of", "finery", "she", "was", "therefore", "obliged",
	        "to", "seek", "another", "branch", "of", "the", "subject", "and", "related", "with", "much",
	        "bitterness", "of", "spirit", "and", "some", "exaggeration", "the", "shocking", "rudeness", "of", "mr",
	        "darcy", "but", "i", "can", "assure", "you", "she", "added", "that", "lizzy", "does", "not", "lose",
	        "much", "by", "not", "suiting", "his", "fancy", "for", "he", "is", "a", "most", "disagreeable",
	        "horrid", "man", "not", "at", "all", "worth", "pleasing", "so", "high", "and", "so", "conceited",
	        "that", "there", "was", "no", "enduring", "him", "he", "walked", "here", "and", "he", "walked", "there",
	        "fancying", "himself", "so", "very", "great", "not", "handsome", "enough", "to", "dance", "with", "i",
	        "wish", "you", "had", "been", "there", "my", "dear", "to", "have", "given", "him", "one", "of", "your",
	        "set", "downs", "i", "quite", "detest", "the", "man", "chapter", "when", "jane", "and", "elizabeth",
	        "were", "alone", "the", "former", "who", "had", "been", "cautious", "in", "her", "praise", "of", "mr",
	        "bingley", "before", "expressed", "to", "her", "sister", "just", "how", "very", "much", "she",
	        "admired", "him", "he", "is", "just", "what", "a", "young", "man", "ought", "to", "be", "said", "she",
	        "sensible", "good", "humoured", "lively", "and", "i", "never", "saw", "such", "happy", "manners", "so",
	        "much", "ease", "with", "such", "perfect", "good", "breeding", "he", "is", "also", "handsome",
	        "replied", "elizabeth", "which", "a", "young", "man", "ought", "likewise", "to", "be", "if", "he",
	        "possibly", "can", "his", "character", "is", "thereby", "complete", "i", "was", "very", "much",
	        "flattered", "by", "his", "asking", "me", "to", "dance", "a", "second", "time", "i", "did", "not",
	        "expect", "such", "a", "compliment", "did", "not", "you", "i", "did", "for", "you", "but", "that", "is",
	        "one", "great", "difference", "between", "us", "compliments", "always", "take", "you", "by", "surprise",
	        "and", "me", "never", "what", "could", "be", "more", "natural", "than", "his", "asking", "you", "again",
	        "he", "could", "not", "help", "seeing", "that", "you", "were", "about", "five", "times", "as", "pretty",
	        "as", "every", "other", "woman", "in", "the", "room", "no", "thanks", "to", "his", "gallantry", "for",
	        "that", "well", "he", "certainly", "is", "very", "agreeable", "and", "i", "give", "you", "leave", "to",
	        "like", "him", "you", "have", "liked", "many", "a", "stupider", "person", "dear", "lizzy", "oh", "you",
	        "are", "a", "great", "deal", "too", "apt", "you", "know", "to", "like", "people", "in", "general",
	        "you", "never", "see", "a", "fault", "in", "anybody", "all", "the", "world", "are", "good", "and",
	        "agreeable", "in", "your", "eyes", "i", "never", "heard", "you", "speak", "ill", "of", "a", "human",
	        "being", "in", "your", "life", "i", "would", "not", "wish", "to", "be", "hasty", "in", "censuring",
	        "anyone", "but", "i", "always", "speak", "what", "i", "think", "i", "know", "you", "do", "and", "it",
	        "is", "that", "which", "makes", "the", "wonder", "with", "your", "good", "sense", "to", "be", "so",
	        "honestly", "blind", "to", "the", "follies", "and", "nonsense", "of", "others", "affectation", "of",
	        "candour", "is", "common", "enough", "one", "meets", "with", "it", "everywhere", "but", "to", "be",
	        "candid", "without", "ostentation", "or", "design", "to", "take", "the", "good", "of", "everybodys",
	        "character", "and", "make", "it", "still", "better", "and", "say", "nothing", "of", "the", "bad",
	        "belongs", "to", "you", "alone", "and", "so", "you", "like", "this", "mans", "sisters", "too", "do",
	        "you", "their", "manners", "are", "not", "equal", "to", "his", "certainly", "not", "at", "first", "but",
	        "they", "are", "very", "pleasing", "women", "when", "you", "converse", "with", "them", "miss",
	        "bingley", "is", "to", "live", "with", "her", "brother", "and", "keep", "his", "house", "and", "i",
	        "am", "much", "mistaken", "if", "we", "shall", "not", "find", "a", "very", "charming", "neighbour",
	        "in", "her", "elizabeth", "listened", "in", "silence", "but", "was", "not", "convinced", "their",
	        "behaviour", "at", "the", "assembly", "had", "not", "been", "calculated", "to", "please", "in",
	        "general", "and", "with", "more", "quickness", "of", "observation", "and", "less", "pliancy", "of",
	        "temper", "than", "her", "sister", "and", "with", "a", "judgement", "too", "unassailed", "by", "any",
	        "attention", "to", "herself", "she", "was", "very", "little", "disposed", "to", "approve", "them",
	        "they", "were", "in", "fact", "very", "fine", "ladies", "not", "deficient", "in", "good", "humour",
	        "when", "they", "were", "pleased", "nor", "in", "the", "power", "of", "making", "themselves",
	        "agreeable", "when", "they", "chose", "it", "but", "proud", "and", "conceited", "they", "were",
	        "rather", "handsome", "had", "been", "educated", "in", "one", "of", "the", "first", "private",
	        "seminaries", "in", "town", "had", "a", "fortune", "of", "twenty", "thousand", "pounds", "were", "in",
	        "the", "habit", "of", "spending", "more", "than", "they", "ought", "and", "of", "associating", "with",
	        "people", "of", "rank", "and", "were", "therefore", "in", "every", "respect", "entitled", "to", "think",
	        "well", "of", "themselves", "and", "meanly", "of", "others", "they", "were", "of", "a", "respectable",
	        "family", "in", "the", "north", "of", "england", "a", "circumstance", "more", "deeply", "impressed",
	        "on", "their", "memories", "than", "that", "their", "brothers", "fortune", "and", "their", "own", "had",
	        "been", "acquired", "by", "trade", "mr", "bingley", "inherited", "property", "to", "the", "amount",
	        "of", "nearly", "a", "hundred", "thousand", "pounds", "from", "his", "father", "who", "had", "intended",
	        "to", "purchase", "an", "estate", "but", "did", "not", "live", "to", "do", "it", "mr", "bingley",
	        "intended", "it", "likewise", "and", "sometimes", "made", "choice", "of", "his", "county", "but", "as",
	        "he", "was", "now", "provided", "with", "a", "good", "house", "and", "the", "liberty", "of", "a",
	        "manor", "it", "was", "doubtful", "to", "many", "of", "those", "who", "best", "knew", "the", "easiness",
	        "of", "his", "temper", "whether", "he", "might", "not", "spend", "the", "remainder", "of", "his",
	        "days", "at", "netherfield", "and", "leave", "the", "next", "generation", "to", "purchase", "his",
	        "sisters", "were", "anxious", "for", "his", "having", "an", "estate", "of", "his", "own", "but",
	        "though", "he", "was", "now", "only", "established", "as", "a", "tenant", "miss", "bingley", "was",
	        "by", "no", "means", "unwilling", "to", "preside", "at", "his", "table", "nor", "was", "mrs", "hurst",
	        "who", "had", "married", "a", "man", "of", "more", "fashion", "than", "fortune", "less", "disposed",
	        "to", "consider", "his", "house", "as", "her", "home", "when", "it", "suited", "her", "mr", "bingley",
	        "had", "not", "been", "of", "age", "two", "years", "when", "he", "was", "tempted", "by", "an",
	        "accidental", "recommendation", "to", "look", "at", "netherfield", "house", "he", "did", "look", "at",
	        "it", "and", "into", "it", "for", "half", "an", "hour", "was", "pleased", "with", "the", "situation",
	        "and", "the", "principal", "rooms", "satisfied", "with", "what", "the", "owner", "said", "in", "its",
	        "praise", "and", "took", "it", "immediately", "between", "him", "and", "darcy", "there", "was", "a",
	        "very", "steady", "friendship", "in", "spite", "of", "great", "opposition", "of", "character",
	        "bingley", "was", "endeared", "to", "darcy", "by", "the", "easiness", "openness", "and", "ductility",
	        "of", "his", "temper", "though", "no", "disposition", "could", "offer", "a", "greater", "contrast",
	        "to", "his", "own", "and", "though", "with", "his", "own", "he", "never", "appeared", "dissatisfied",
	        "on", "the", "strength", "of", "darcys", "regard", "bingley", "had", "the", "firmest", "reliance",
	        "and", "of", "his", "judgement", "the", "highest", "opinion", "in", "understanding", "darcy", "was",
	        "the", "superior", "bingley", "was", "by", "no", "means", "deficient", "but", "darcy", "was", "clever",
	        "he", "was", "at", "the", "same", "time", "haughty", "reserved", "and", "fastidious", "and", "his",
	        "manners", "though", "well", "bred", "were", "not", "inviting", "in", "that", "respect", "his",
	        "friend", "had", "greatly", "the", "advantage", "bingley", "was", "sure", "of", "being", "liked",
	        "wherever", "he", "appeared", "darcy", "was", "continually", "giving", "offense", "the", "manner", "in",
	        "which", "they", "spoke", "of", "the", "meryton", "assembly", "was", "sufficiently", "characteristic",
	        "bingley", "had", "never", "met", "with", "more", "pleasant", "people", "or", "prettier", "girls", "in",
	        "his", "life", "everybody", "had", "been", "most", "kind", "and", "attentive", "to", "him", "there",
	        "had", "been", "no", "formality", "no", "stiffness", "he", "had", "soon", "felt", "acquainted", "with",
	        "all", "the", "room", "and", "as", "to", "miss", "bennet", "he", "could", "not", "conceive", "an",
	        "angel", "more", "beautiful", "darcy", "on", "the", "contrary", "had", "seen", "a", "collection", "of",
	        "people", "in", "whom", "there", "was", "little", "beauty", "and", "no", "fashion", "for", "none", "of",
	        "whom", "he", "had", "felt", "the", "smallest", "interest", "and", "from", "none", "received", "either",
	        "attention", "or", "pleasure", "miss", "bennet", "he", "acknowledged", "to", "be", "pretty", "but",
	        "she", "smiled", "too", "much", "mrs", "hurst", "and", "her", "sister", "allowed", "it", "to", "be",
	        "so", "but", "still", "they", "admired", "her", "and", "liked", "her", "and", "pronounced", "her", "to",
	        "be", "a", "sweet", "girl", "and", "one", "whom", "they", "would", "not", "object", "to", "know",
	        "more", "of", "miss", "bennet", "was", "therefore", "established", "as", "a", "sweet", "girl", "and",
	        "their", "brother", "felt", "authorized", "by", "such", "commendation", "to", "think", "of", "her",
	        "as", "he", "chose", "chapter", "within", "a", "short", "walk", "of", "longbourn", "lived", "a",
	        "family", "with", "whom", "the", "bennets", "were", "particularly", "intimate", "sir", "william",
	        "lucas", "had", "been", "formerly", "in", "trade", "in", "meryton", "where", "he", "had", "made", "a",
	        "tolerable", "fortune", "and", "risen", "to", "the", "honour", "of", "knighthood", "by", "an",
	        "address", "to", "the", "king", "during", "his", "mayoralty", "the", "distinction", "had", "perhaps",
	        "been", "felt", "too", "strongly", "it", "had", "given", "him", "a", "disgust", "to", "his", "business",
	        "and", "to", "his", "residence", "in", "a", "small", "market", "town", "and", "in", "quitting", "them",
	        "both", "he", "had", "removed", "with", "his", "family", "to", "a", "house", "about", "a", "mile",
	        "from", "meryton", "denominated", "from", "that", "period", "lucas", "lodge", "where", "he", "could",
	        "think", "with", "pleasure", "of", "his", "own", "importance", "and", "unshackled", "by", "business",
	        "occupy", "himself", "solely", "in", "being", "civil", "to", "all", "the", "world", "for", "though",
	        "elated", "by", "his", "rank", "it", "did", "not", "render", "him", "supercilious", "on", "the",
	        "contrary", "he", "was", "all", "attention", "to", "everybody", "by", "nature", "inoffensive",
	        "friendly", "and", "obliging", "his", "presentation", "at", "st", "jamess", "had", "made", "him",
	        "courteous", "lady", "lucas", "was", "a", "very", "good", "kind", "of", "woman", "not", "too", "clever",
	        "to", "be", "a", "valuable", "neighbour", "to", "mrs", "bennet", "they", "had", "several", "children",
	        "the", "eldest", "of", "them", "a", "sensible", "intelligent", "young", "woman", "about", "twenty",
	        "seven", "was", "elizabeths", "intimate", "friend", "that", "the", "miss", "lucases", "and", "the",
	        "miss", "bennets", "should", "meet", "to", "talk", "over", "a", "ball", "was", "absolutely",
	        "necessary", "and", "the", "morning", "after", "the", "assembly", "brought", "the", "former", "to",
	        "longbourn", "to", "hear", "and", "to", "communicate", "you", "began", "the", "evening", "well",
	        "charlotte", "said", "mrs", "bennet", "with", "civil", "self", "command", "to", "miss", "lucas", "you",
	        "were", "mr", "bingleys", "first", "choice", "yes", "but", "he", "seemed", "to", "like", "his",
	        "second", "better", "oh", "you", "mean", "jane", "i", "suppose", "because", "he", "danced", "with",
	        "her", "twice", "to", "be", "sure", "that", "did", "seem", "as", "if", "he", "admired", "her", "indeed",
	        "i", "rather", "believe", "he", "did", "i", "heard", "something", "about", "it", "but", "i", "hardly",
	        "know", "what", "something", "about", "mr", "robinson", "perhaps", "you", "mean", "what", "i",
	        "overheard", "between", "him", "and", "mr", "robinson", "did", "not", "i", "mention", "it", "to", "you",
	        "mr", "robinsons", "asking", "him", "how", "he", "liked", "our", "meryton", "assemblies", "and",
	        "whether", "he", "did", "not", "think", "there", "were", "a", "great", "many", "pretty", "women", "in",
	        "the", "room", "and", "which", "he", "thought", "the", "prettiest", "and", "his", "answering",
	        "immediately", "to", "the", "last", "question", "oh", "the", "eldest", "miss", "bennet", "beyond", "a",
	        "doubt", "there", "cannot", "be", "two", "opinions", "on", "that", "point", "upon", "my", "word",
	        "well", "that", "is", "very", "decided", "indeed", "that", "does", "seem", "as", "if", "but", "however",
	        "it", "may", "all", "come", "to", "nothing", "you", "know", "my", "overhearings", "were", "more", "to",
	        "the", "purpose", "than", "yours", "eliza", "said", "charlotte", "mr", "darcy", "is", "not", "so",
	        "well", "worth", "listening", "to", "as", "his", "friend", "is", "he", "poor", "eliza", "to", "be",
	        "only", "just", "tolerable", "i", "beg", "you", "would", "not", "put", "it", "into", "lizzys", "head",
	        "to", "be", "vexed", "by", "his", "ill", "treatment", "for", "he", "is", "such", "a", "disagreeable",
	        "man", "that", "it", "would", "be", "quite", "a", "misfortune", "to", "be", "liked", "by", "him", "mrs",
	        "long", "told", "me", "last", "night", "that", "he", "sat", "close", "to", "her", "for", "half", "an",
	        "hour", "without", "once", "opening", "his", "lips", "are", "you", "quite", "sure", "maam", "is", "not",
	        "there", "a", "little", "mistake", "said", "jane", "i", "certainly", "saw", "mr", "darcy", "speaking",
	        "to", "her", "aye", "because", "she", "asked", "him", "at", "last", "how", "he", "liked", "netherfield",
	        "and", "he", "could", "not", "help", "answering", "her", "but", "she", "said", "he", "seemed", "quite",
	        "angry", "at", "being", "spoke", "to", "miss", "bingley", "told", "me", "said", "jane", "that", "he",
	        "never", "speaks", "much", "unless", "among", "his", "intimate", "acquaintances", "with", "them", "he",
	        "is", "remarkably", "agreeable", "i", "do", "not", "believe", "a", "word", "of", "it", "my", "dear",
	        "if", "he", "had", "been", "so", "very", "agreeable", "he", "would", "have", "talked", "to", "mrs",
	        "long", "but", "i", "can", "guess", "how", "it", "was", "everybody", "says", "that", "he", "is", "eat",
	        "up", "with", "pride", "and", "i", "dare", "say", "he", "had", "heard", "somehow", "that", "mrs",
	        "long", "does", "not", "keep", "a", "carriage", "and", "had", "come", "to", "the", "ball", "in", "a",
	        "hack", "chaise", "i", "do", "not", "mind", "his", "not", "talking", "to", "mrs", "long", "said",
	        "miss", "lucas", "but", "i", "wish", "he", "had", "danced", "with", "eliza", "another", "time", "lizzy",
	        "said", "her", "mother", "i", "would", "not", "dance", "with", "him", "if", "i", "were", "you", "i",
	        "believe", "maam", "i", "may", "safely", "promise", "you", "never", "to", "dance", "with", "him", "his",
	        "pride", "said", "miss", "lucas", "does", "not", "offend", "me", "so", "much", "as", "pride", "often",
	        "does", "because", "there", "is", "an", "excuse", "for", "it", "one", "cannot", "wonder", "that", "so",
	        "very", "fine", "a", "young", "man", "with", "family", "fortune", "everything", "in", "his", "favour",
	        "should", "think", "highly", "of", "himself", "if", "i", "may", "so", "express", "it", "he", "has", "a",
	        "right", "to", "be", "proud", "that", "is", "very", "true", "replied", "elizabeth", "and", "i", "could",
	        "easily", "forgive", "his", "pride", "if", "he", "had", "not", "mortified", "mine", "pride", "observed",
	        "mary", "who", "piqued", "herself", "upon", "the", "solidity", "of", "her", "reflections", "is", "a",
	        "very", "common", "failing", "i", "believe", "by", "all", "that", "i", "have", "ever", "read", "i",
	        "am", "convinced", "that", "it", "is", "very", "common", "indeed", "that", "human", "nature", "is",
	        "particularly", "prone", "to", "it", "and", "that", "there", "are", "very", "few", "of", "us", "who",
	        "do", "not", "cherish", "a", "feeling", "of", "self", "complacency", "on", "the", "score", "of", "some",
	        "quality", "or", "other", "real", "or", "imaginary", "vanity", "and", "pride", "are", "different",
	        "things", "though", "the", "words", "are", "often", "used", "synonymously", "a", "person", "may", "be",
	        "proud", "without", "being", "vain", "pride", "relates", "more", "to", "our", "opinion", "of",
	        "ourselves", "vanity", "to", "what", "we", "would", "have", "others", "think", "of", "us", "if", "i",
	        "were", "as", "rich", "as", "mr", "darcy", "cried", "a", "young", "lucas", "who", "came", "with", "his",
	        "sisters", "i", "should", "not", "care", "how", "proud", "i", "was", "i", "would", "keep", "a", "pack",
	        "of", "foxhounds", "and", "drink", "a", "bottle", "of", "wine", "a", "day", "then", "you", "would",
	        "drink", "a", "great", "deal", "more", "than", "you", "ought", "said", "mrs", "bennet", "and", "if",
	        "i", "were", "to", "see", "you", "at", "it", "i", "should", "take", "away", "your", "bottle",
	        "directly", "the", "boy", "protested", "that", "she", "should", "not", "she", "continued", "to",
	        "declare", "that", "she", "would", "and", "the", "argument", "ended", "only", "with", "the", "visit" };

	return austin;
    }
}
