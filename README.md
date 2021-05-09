# throttletool

under /throttletool/src/main/java
Class: com.clsa.codingtest.throttletool.MarketDataProcessor
The implementation of market data processor. publish() method will read a MarketData object from the queue, and check if it fulfills the criteria to publish, 
then call the publishAggregatedMarketData() method to publish, otherwise, push it back to the queue for later publish.

under /throttletool/src/test/java
Class: com.clsa.codingtest.throttletool.MarketDataProcessorTest
Main class for testing, entry method is main(), it will :- 
1. create a mock MarketDataProcessor class, and start the publishing method.
2. generate random MarketData objects, and call the onMessage() to send it to market data processor.

Class: com.clsa.codingtest.throttletool.MockMarketDataProcessor
Mock MarketDataProcessor class for testing, with overriden publishAggregatedMarketData method, which prints the market data to being published to the standard output.

