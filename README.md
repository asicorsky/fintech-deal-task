# fintech-deal-task

## Require:
1. Java 14
2. Maven 3

## Install:
Open command line, go to project root and execute "mvn clean install"

## Execute:
Go to the target directory in command line which created after install and execute "java -jar fintech-deal-task.jar"

#### ...or you can just import project in IntelliJ and start ApplicationEntryPoint

## Libraries which used:
1. Jackson - for json processing because life is too short
2. Guava - for EventBus implementation because I wanted to try it for a long time
3. Logback - for logging

## How it works
1. System scan resource each minute
2. As soon as stream with interested resources (bids in current case) was ready, system trigger an event (STREAM_READY) via event bus
3. Event bus handle this event and spawn parallel events (BID) via CompletableFuture and ForkJoinPool for each bid
4. Event bus handle BID events and create new thread per each queue based on type if it needed, or add to existing queue
5. System process queue and log the data from the bid
6. In case if bids for specified type was not updated for a 10 minutes (currently hardcoded, but may externalized), system salvate related thread
7. In case if bid comes after 10 minutes, system was create new thread 
