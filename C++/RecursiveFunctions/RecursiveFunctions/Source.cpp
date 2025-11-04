// STEVE PAV

#include <iostream>
#include <string>

// Determines how many times a given digit appears in an integer value
int Appearances(int value, int search)
{
	// Unwind all "recursiveness"
	if (value <= 0)
	{
		return 0;
	}

	// Get last digit of value
	int digit = value % 10;

	// Remove last value 
	value /= 10; 

	if (digit == search)
	{
		// Add 1 to the return value which is count of appearances
		return Appearances(value, search) + 1;
	}
	
	return Appearances(value, search);
}


// Turns a number into its base 6 string equivalent
std::string IntegerToBase6(int value)
{
	// Unwind all "recursiveness"
	if (value <= 0)
	{
		return "";
	}

	// Get remainder of dividing value by 6
	std::string mod = std::to_string(value % 6);

	// Call function again and add remainder to end
	return IntegerToBase6(value / 6) + mod;
}


// Display menu options
void PrintMenu()
{
	std::cout << "1. Test Appearances" << std::endl;
	std::cout << "2. Test IntegerToBase6" << std::endl;
	std::cout << "Enter anything else to quit" << std::endl;
	std::cout << "Enter choice: ";
}


// Test Appearances function
void TestAppearances()
{
	int value, search;
	std::cout << "Enter value: ";
	std::cin >> value;
	std::cout << "Enter single digit integer to search for: ";
	std::cin >> search;

	std::cout << search << " appears in " << value << " " << Appearances(value, search) << " time(s)." << std::endl;
}


// Test IntegerToBase6 function
void TestIntegerToBase6()
{
	int val;
	std::cout << "Enter value: ";
	std::cin >> val;

	std::cout << val << " to base 6 is " << IntegerToBase6(val) << std::endl;
}


int main()
{
	int input;

	do
	{
		PrintMenu();

		std::cin >> input;

		switch (input)
		{
			
		// Test Appearances function
		case 1:
			TestAppearances();
			break;

		// Test IntegerToBase6 function
		case 2:
			TestIntegerToBase6();
			break;

		// Do nothing (exit program)
		default:
			break;
		}

		// Blank line
		std::cout << std::endl;

	} while (input == 1 || input == 2);


	return 0;
}