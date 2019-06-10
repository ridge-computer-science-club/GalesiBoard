/**
    Writes text into the provided 2-dimensional CRGB array.

    @param text[] The string of text to display.
    @param colour The colour of the text.
    @param x The X location of the text on the board.
    @param y The y location of the text on the board.
    @param &ledArray Reference to a matrix (2-Dimensional Array) of CRGB Objects that makes up the board. In other words, it's an array of arrays that were passed to FastLED.
*/
void writeText(const char text[], const CRGB colour, const size_t x, const size_t y, CRGB (&ledArray)[ROWS][COLS])
{
    // Loop through each character in the given string of text.
    for (int textIndex = 0; textIndex < strlen(text); ++textIndex)
    {
        // The
        uint8_t asciiCodeOffset;
        if (text[textIndex] == ' ')
        {
            asciiCodeOffset = 26;
        }
        else if (text[textIndex] == '!')
        {
            asciiCodeOffset = 27;
        }
        else
        {
            asciiCodeOffset = text[textIndex] - STARTING_CHAR;
        }

        for (int characterRow = 0; characterRow < CHARACTER_SIZE; ++characterRow)
        {

            for (int bit = 0; bit < CHARACTER_SIZE; ++bit)
            {
                const size_t ledRow = characterRow + (ROWS - CHARACTER_SIZE) - y;
                // Check if text overflows array, break to prevent writing out of bounds.
                if (ledRow > (ROWS - 1))
                    break;
                if (ledRow < 0)
                    continue;

                const size_t ledCol = (CHARACTER_SIZE - (bit + 1)) + ((CHARACTER_SIZE + 1) * textIndex) + x;
                // Check if text overflows array, break to prevent writing out of bounds.
                if (ledCol > (COLS - 1))
                    continue;
                if (ledCol < 0)
                    break;
                //
                const bool isPixel = (characters[asciiCodeOffset][characterRow] >> bit) & 0x1;
                ledArray[ledRow][ledCol] = isPixel ? colour : CRGB::Black;
            }
        }
    }
}