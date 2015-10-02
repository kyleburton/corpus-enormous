# corpus-enormous

A Clojure library for generating sample data sets.

Supports:

* US / English Names
* US Phone Numbers
* US Addresses
* Email Addresses based on US / English Names

Roadmap:

* Java API

## Usage

<pre>
> lein repl
(require 'corpus-enormous.people)
nREPL server started on port 48230 on host 127.0.0.1 - nrepl://127.0.0.1:48230
REPL-y 0.3.2, nREPL 0.2.11
Clojure 1.7.0
Java HotSpot(TM) 64-Bit Server VM 1.8.0_31-b13
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e

user=> (require 'corpus-enormous.people)
nil
user=> (corpus-enormous.people/random-person)
{:street1 "742 W. Amodeo Parkway", :email "dnayes5@spamgoes.in", :last-name "NAYES", :city "SUNRAY", :ssn "497-76-900", :state "TX", :first-name "DEON", :street2 nil, :zip "79086", :gender "M"}
user=> 
</pre>

## License

Copyright © 2015 Kyle Burton kyle.burton@gmail.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
