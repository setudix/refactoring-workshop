## FizzBuzz.java

- Moved the functionality of the modulus operator to a new method `canDivide()` so that it is easier to read and understand.

## PlaintextToHtmlConverter.java

-`i` is misleading naming. It is a code smell. It is **inappropriate naming**. I changed it to a more relevant `currentLocation`.
- There is no need for a temporary variable `text` in method `toHtml()`, so I removed it.
- Extracted the boolean statement of the `while` loop to a method `isCurrentLocationOutOfBounds()` for better readability.
- `if (currentLocation >= source.length()) break;` is **deadcode**, so I removed it.
- There are several comments in the code explaining the functionality. So these need to be removed and the code must be rewritten in a way that explains itself.
- Moved `currentLocation += 1` to a new method `incrementCurrentLocation()` for better readability.
- Fixed **inappropriate naming** in `stashNextCharacterAndAdvanceThePointer()`.
- Added `getFinalResult()` method for better readability.
## TriviaGame.java

- Removed **dead code** `isPlayable()`
- Chaged `roll % 2 != 0`to `isRollOdd(roll)`. (change temp with query)
- Added `incrementPlaces(roll)` instead of `places[currentPlayer] = places[currentPlayer] + roll` for better readability.
- Added `if (needToReducePlaces(currentPlayer))` instead of `if (places[currentPlayer] > 11)` for better readability.
- Added `reducePlaces()` instead of `places[currentPlayer] = places[currentPlayer] - 12`
- Fixed **duplicate code** for instances in `void roll(int roll)` method.
-  Fixed **switch statements** smell with appropriate methods `isPop()`, `isScience()`, and `isSports()`
- `incrementCurrentPlayer()` method instead of `currentPlayer++`
- `if (isPlayerSizedReached())` instead of `if (currentPlayer == players.size())`
 
 
 