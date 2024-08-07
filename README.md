# corpus-enormous

A Clojure library for generating sample data sets.

Supports:

* US / English Names
* US Phone Numbers
* US Addresses
 * City, State and Zip5's are real, though Street 1 and Street 2 data is fabricated
* Email Addresses based on US / English Names

Roadmap:

* Data: obtain a better list of domain names
* Data: obtain a list of sensible us street names
* Util: Commandline interface for generating sample data sets.
* Lib:  DSL for generators based on the internal data sets
* Lib:  registry for additional data sets
* Lib:  Namespace to com.github.kyleburton and release to clojars
* Lib:  Java API
* Lib:  benchmarking
* Lib:  explore code-generation for DSL and string templates (performance)
* Lib:  allow users to trigger eager vs lazy initialization (prime the caches)
* Gen:  dummy credit card numbers that adhere to the checksum algorithm

## Usage

[![Clojars Project](https://img.shields.io/clojars/v/corpus-enormous.svg)](https://clojars.org/corpus-enormous)

```Clojure
> lein repl
user=> (require 'corpus-enormous.people)
nil
user=> (corpus-enormous.people/random-person)
{:street1 "3 South Mogan Avenue", :email "pearl.galicia@sweetxxx.de", :last-name "GALICIA", :phone "(202) 793-2635", :city "DAVY", :ssn "492-95-1989", :state "WV", :first-name "PEARL", :street2 nil, :zip "24828", :gender "F"}
user=> (require 'clojure.pprint)
nil
user=> (->> corpus-enormous.people/random-person repeatedly (map clojure.pprint/pprint) (take 5))
{:street1 "39 Khazdozian Hwy",
 :email "g.plys74@armyspy.com",
 :last-name "PLYS",
 :phone "520 762-9290",
 :city "ELIZABETHVILLE",
 :ssn "863-63-3975",
 :state "PA",
 :first-name "GWEN",
 :street2 nil,
 :zip "17023",
 :gender "F"}
{:street1 "409 Tschoepe Run",
 :email "s.peeks96@fastchevy.com",
 :last-name "PEEKS",
 :phone "1 (268) 744-6098",
 :city "RALEIGH",
 :ssn "153-11-6780",
 :state "NC",
 :first-name "SEBASTIAN",
 :street2 nil,
 :zip "27629",
 :gender "M"}
{:street1 "100 Mika Point",
 :email "reneeg94@fuckingduh.com",
 :last-name "GOODE",
 :phone "438.26.4937",
 :city "MARENGO",
 :ssn "225-43-9338",
 :state "WI",
 :first-name "RENEE",
 :street2 nil,
 :zip "54855",
 :gender "F"}
{:street1 "67 N. Testolin Crossing",
 :email "s.agustino39@hidzz.com",
 :last-name "AGUSTINO",
 :phone "3800505699",
 :city "ORANGE",
 :ssn "932-40-6097",
 :state "TX",
 :first-name "SONNY",
 :street2 nil,
 :zip "77631",
 :gender "M"}
{:street1 "7 Quadnau Pkwy",
 :email "c.dangelis44@teleworm.us",
 :last-name "DANGELIS",
 :phone "623 941-6299",
 :city "BROWNTOWN",
 :ssn "135-47-7538",
 :state "WI",
 :first-name "CHUN",
 :street2 nil,
 :zip "53522",
 :gender "F"}
(nil nil nil nil nil)
user=>
```

## Data Sources

* https://www.census.gov/library/reference/code-lists/ansi/ansi-codes-for-states.html


## License

Copyright © 2015-2024 Kyle Burton kyle.burton@gmail.com

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
