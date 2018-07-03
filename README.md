# Lucene-Project
The task is to use Lucene to index and search a specific collection of documents. 

Content
The content in question is an aggregated collection of news articles (mostly) from a number of sources: the Financial Times Limited (1991, 1992, 1993, 1994), the Federal Register (1994), the Foreign Broadcast Information Service (1996) and the Los Angeles Times (1989, 1990). You can download the full content collection from here - https://drive.google.com/a/tcd.ie/file/d/1MudJity9Ckh8jxapFx3OS-DLEkcvbYYx/view?usp=drive_web 

Indexing
Through the first assignment, you already know the basics of how to index a collection. It is now time to start to think in more depth about the indexing process. 
What fields (if any) should the parsed documents be separated into? What stop word removal, stemming, phrase identification and other analysers should be used? Are there more complex linguistic modelling processes that could be used? 

Queries 
In assignment one the queries were simple, pre-defined short strings of text. 
In many TREC tasks, however, instead of a defined query, you are given a "topic". Each topic represents a user's information need. An example TREC topic is below:
<top>

    <num> Number: 404    

    <title> Ireland, peace talks    

    <desc> Description:  How often were the peace talks in Ireland delayed  or disrupted as a result of acts of violence?

    <narr> Narrative:  Any interruptions to the peace process not directly  attributable to acts of violence are not relevant.

</top>

Binary relevance judgements are made against these topics, rather than against specified queries. In other words, documents in the collection are judged based upon whether they satisfy the underlying information need. The topics for assignment two can be found here - https://www.dropbox.com/s/277vn6l23z2e6ku/CS7IS3-Assignment2-Topics.gz?dl=0 
You need to take these topics and automatically generate a query (or queries) that best represents the information need described in the topic, and returns the most relevant results. You can experiment with a number of approaches, just using the title, using the description, using a combination of all three fields, trying to identify the best information carrying words in the topic and generating a query using those. You can also experiment with more advanced approaches such as query expansion.
Whatever approach you decide upon, should generate one query per topic, and return the best 1000 results for that topic. 

Retrieval Model 
The retrieval model used can significantly impact the performance of your search engine. You should experiment with a variety of retrieval models to identify which performs best for this collection and these topics. You should also consider if the retrieval models could be extended in any way to improve the performance of your search engine for this content. 

Information about the project and instruction of running project.

Configuration.java: Including the paths of data files, query files and index files.

DocumentCollection.java: Document Class of four kinds of document.

DocumentCollectionReader: Read DocumentCollections and add them to parser.

DocumentQuery.java: Query Class.


We have four different parser of documents:

1.FBISDocumentParser.java

2.FR94DocumentParser.java

3.FTDocumentParser.java

4.LATIMESDocumentParser.java


IndexIterator.java: For the iteration of generation index.

Main.java: Main function.

ResultGenerator.java: Generate results file for trec_eval format. The result file is in luceneTest/files/

TopicParser.java: Parse Topics and form queries.


Run project:

1. Use "gradle build" to build the project.

2. Use "gradle run" to get result.

Deploy it in AWS:

1. Clone the project on AWS.

2. Use 'gradle build', 'gradle run' to exectue the program.

