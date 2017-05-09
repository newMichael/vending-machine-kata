Instructions

All kata requirements do not need to be coded for the kata to be submitted, but requirements implemented should be implemented with best practices. This is a showcase of skill, not speed. 

The candidate should let us know what kata requirements were implemented when submitting. 

The candidate should spend two hours (maximum) on each kata. 

All code should be test driven and committed.

Download empty project files:
java_audition.bundle
(more information about git bundle here: http://git-scm.com/blog/2010/03/10/bundles.html)

The file is a compressed git repository of an empty project that you will be working within.  Maven is included and you can use it to run your mvn build and mvn test.

After the files are downloaded, extract them with git with the following command(s):

> git clone java_audition.bundle -b master [candidates_name]_java_audition

for example:
> git clone java_audition.bundle -b master danmonroe_java_audition

creates a danmonroe_java_audition directory


Complete the katas assigned to you.  Commit your changes as you develop.


When complete, create a new bundle to send back to us using the following commands:
> git bundle create [candidates_name]_java_audition.bundle master

for example:
> git bundle create danmonroe_java_audition.bundle master

verify:
> git bundle verify [candidates_name]_java_audition.bundle

example:
> git bundle verify danmonroe_java_audition.bundle

Should say: danmonroe_java_audition.bundle is okay

Send the completed bundle file via email.

Accept Coins

As a vendor

I want a vending machine that accepts coins So that I can collect money from the customer
The vending machine will accept valid coins (nickels, dimes, and quarters) and reject invalid ones (pennies). When a valid coin is inserted the amount of the coin will be added to the current amount and the display will be updated. When there are, no coins inserted, the machine displays INSERT COIN. Rejected coins are placed in the coin return.
NOTE: The temptation here will be to create Coin objects that know their value. However, this is not how a real vending machine works. Instead, it identifies coins by their weight and size and then assigns a value to what was inserted. You will need to do something similar. This can be simulated using strings, constants, enums, symbols, or something of that nature.