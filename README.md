# nearest-word-generator
## Loading a new dictionary into memory
<p>The dictionary can be updated via a PUT request at https://nearest-word-generator.herokuapp.com/reformat with the encoded URL for the <strong>link</strong> parameter. <br>

An example would be: <br>
https://nearest-word-generator.herokuapp.com/reformat?link=https%3A%2F%2Fraw.githubusercontent.com%2Fdwyl%2Fenglish-words%2Fmaster%2Fwords_alpha.txt</p>

## Generating nearest words
<p>Generating the nearest words for a given parameters for <strong> word, delta, and number</strong> can be done by sending a get request to 
https://nearest-word-generator.herokuapp.com/generate

An example would be: <br>
https://nearest-word-generator.herokuapp.com/generate?word=car&delta=1&number=2
 </p>