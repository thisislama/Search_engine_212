
public class Document{
  LinkedList<String>words=new LinkedList<>();
  int id;
  String content;

  //mainly for index
  //document with id and linked list of words except stop words
  public Document(int id,LinkedList<String>words , String content){
  this.id=id;
this.words=words;
this.content = content;
  }
  
  }



