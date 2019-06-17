/*
This is the program to control GalesiBoard. It currently displays animated text on the board.
*/

// These statements include the libraries that we are using, and tell the compiler that we want to use code from them. 'Libraries' are pieces of code that have already been written (typically by someone other than yourself), so that you don't have to write everything from scratch every time you want to make a program.

// This library is the Arduino Standard Library, which provides all the basic functionality that the hardware provides (for example: timing, input and output, the loop() and setup() functions).
#include <Arduino.h>

// This is the FastLED library, which is used to control the WS2812B LEDs that are on the board. It provides a lot of very useful functions, and takes care of all the low level data handling for us.
#include <FastLED.h>

// This is the file which contains all of the font data to display text.
#include <characters.h>

// These are constants that we define for our program, such as what type of LEDs we are using, how bright we want the LED to be, and the refresh rate (update rate) of the LED.

// How many LEDs are in each coloumn and row.
constexpr size_t ROWS = 5;
constexpr size_t COLS = 77;

// How bright the LEDs should be, on a scale from 0 to 255.
#define BRIGHTNESS 255
// The specific type of LED being controlled, so the FastLED library knows how to format the transmitted colour data.
#define LED_TYPE WS2812B
// The order in which the LED accepts colour data - [R]ed, [G]reen, [B]lue.
#define COLOR_ORDER RGB

CRGB leds[ROWS][COLS] = {0};
CRGB bottom[COLS];

/**
    Writes text into the provided 2-dimensional CRGB array.

    @param text[] The string of text to display.
    @param colour The colour of the text.
    @param x The X location of the text on the board.
    @param y The y location of the text on the board.
*/
void writeText(const char text[], const CRGB colour, const size_t x, const size_t y, CRGB (&ledArray)[ROWS][COLS])
{
  for (int textIndex = 0; textIndex < strlen(text); ++textIndex)
  {
    const uint8_t asciiCodeOffset = text[textIndex] - STARTING_CHAR;

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

        const size_t ledCol = (bit) + ((CHARACTER_SIZE + 1) * textIndex) + x;
        // Check if text overflows array, break to prevent writing out of bounds.
        if (ledCol > (COLS - 1))
          continue;
        if (ledCol < 0)
          break;
        //
        const bool isPixel = (characters[asciiCodeOffset][characterRow] << bit) & 0b10000000;
        ledArray[ledRow][ledCol] = isPixel? colour : CRGB::Black;
      }
    }
  }
}

void setup()
{
  FastLED.addLeds<LED_TYPE, 13>(leds[0], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 12>(leds[1], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 14>(leds[2], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 27>(leds[3], COLS).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<LED_TYPE, 26>(leds[4], COLS).setCorrection(TypicalLEDStrip);


  FastLED.setBrightness(BRIGHTNESS);

  Serial.begin(9600);
  delay(3000);
  Serial.println("starting");
}

void loop()
{
  /* 
  for (int i = 80; i > 6; i--)
  {
    fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
    if (print.length()<13){  
      writeText(print.c_str(), CRGB::Green, i, 0, leds);}
    else{
      writeText(print.substring(0,print.indexOf(" ")+1).c_str(), CRGB::Green, i, 6, leds);
      writeText(print.substring(print.indexOf(" ")+1).c_str(), CRGB:: Green, i, 0, leds);
    }
    delay(50);
    FastLED.show();
  }
  */
  fill_solid(&(leds[0][0]), ROWS * COLS, CRGB::Black);
  FastLED.show();

  writeText("HI", CRGB::Red, 0, 0, leds);
  FastLED.show();

  delay(3000);
}