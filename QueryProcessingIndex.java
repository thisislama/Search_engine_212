
public class QueryProcessingIndex {
    static Index index1;

    public QueryProcessingIndex(Index index1) {
        this.index1 = index1;
    }

    public static LinkedList<Integer> BooleanQuery(String Query) {
        if (!Query.contains("AND") && !Query.contains("OR"))
            return AndQuery(Query);
        else if (Query.contains("AND") && !Query.contains("OR"))
            return AndQuery(Query);
        else if (!Query.contains("AND") && Query.contains("OR"))
            return ORQuery(Query);
        else
            return MixedQuery(Query);
    }

    public static LinkedList<Integer> MixedQuery(String Query) {
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();
        if (Query.length() == 0) return A;

        String ors[] = Query.split("OR");
        A = AndQuery(ors[0]);

        for (int i = 1; i < ors.length; i++) {
            B = AndQuery(ors[i]);
            A = ORQuery(A, B);
        }
        return A;
    }

    public static LinkedList<Integer> AndQuery(String Query) {
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();
        String terms[] = Query.split("AND");

        if (terms.length == 0) return A;

        A = index1.getDocsGivenTerm(terms[0].trim().toLowerCase());
        for (int i = 1; i < terms.length; i++) {
            B = index1.getDocsGivenTerm(terms[i].trim().toLowerCase());
            A = AndQuery(A, B);
        }
        return A;
    }

    public static LinkedList<Integer> AndQuery(LinkedList<Integer> A, LinkedList<Integer> B) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        if (A.isEmpty() || B.isEmpty()) return result;

        A.findFirst();
        while (true) {
            boolean found = existsIn_result(result, A.retrieve());
            if (!found) {
                B.findFirst();
                while (true) {
                    if (B.retrieve().equals(A.retrieve())) {
                        result.insert(A.retrieve());
                        break;
                    }
                    if (!B.last())
                        B.findNext();
                    else
                        break;
                }
            }
            if (!A.last())
                A.findNext();
            else
                break;
        }
        return result;
    }

    public static LinkedList<Integer> ORQuery(String Query) {
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();
        String terms[] = Query.split("OR");

        if (terms.length == 0) return A;

        A = index1.getDocsGivenTerm(terms[0].trim().toLowerCase());
        for (int i = 1; i < terms.length; i++) {
            B = index1.getDocsGivenTerm(terms[i].trim().toLowerCase());
            A = ORQuery(A, B);
        }
        return A;
    }

    public static LinkedList<Integer> ORQuery(LinkedList<Integer> A, LinkedList<Integer> B) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        if (A.isEmpty() && B.isEmpty()) return result;

        A.findFirst();
        while (!A.isEmpty()) {
            boolean found = existsIn_result(result, A.retrieve());
            if (!found) result.insert(A.retrieve());
            if (!A.last())
                A.findNext();
            else
                break;
        }

        B.findFirst();
        while (!B.isEmpty()) {
            boolean found = existsIn_result(result, B.retrieve());
            if (!found) result.insert(B.retrieve());
            if (!B.last())
                B.findNext();
            else
                break;
        }
        return result;
    }

    public static LinkedList<Integer> notquery(String Query, Index ind1) {
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();
        if (Query.length() == 0) return A;

        if (!Query.contains("NOT")) return A;

        String term = Query.replaceFirst("NOT", "").trim().toLowerCase();
        A = index1.getDocsGivenTerm(term.trim().toLowerCase());

        if (ind1.allDocs.isEmpty()) return A;

        ind1.allDocs.findFirst();
        while (!ind1.allDocs.last()) {
            if (!A.exist(ind1.allDocs.retrieve().id))
                B.insert(ind1.allDocs.retrieve().id);
            ind1.allDocs.findNext();
        }

        if (!A.exist(ind1.allDocs.retrieve().id))
            B.insert(ind1.allDocs.retrieve().id);

        return B;
    }

    public static boolean existsIn_result(LinkedList<Integer> result, Integer id) {
        if (result.isEmpty()) return false;

        result.findFirst();
        while (!result.last()) {
            if (result.retrieve().equals(id)) {
                return true;
            }
            result.findNext();
        }
        return result.retrieve().equals(id);
    }
}
