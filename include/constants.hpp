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

constexpr char START_TEXT = '<'; //0x02;
constexpr char END_TEXT = '>';   //0x03;

constexpr char STARTING_CHAR = ' ';
constexpr uint8_t CHARACTER_SIZE = 5;
