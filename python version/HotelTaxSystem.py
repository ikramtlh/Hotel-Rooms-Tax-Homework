import os

DEFAULT_TAX_RATE = 0.10

def main():
    file_path = input("Please enter the file path containing the room data (e.g., rooms.txt): ")
    print("File path:", file_path)

    response = input("Do you want to specify a custom tax rate? (yes/no): ")

    if response.lower() == "yes":
        tax_rate = float(input("Please enter the tax rate as a percentage (e.g., 15 for 15%): ")) / 100.0
    else:
        tax_rate = DEFAULT_TAX_RATE
        print("The default tax rate is {:.0%}".format(tax_rate))

    if not os.path.isfile(file_path):
        print("Error: File not found. Please check the file path.")
        return

    total_income_before_tax = 0.0
    total_tax = 0.0

    with open(file_path, 'r') as file:
        while True:
            room_type = file.readline().strip()
            if not room_type:
                break
            bookings = int(file.readline().strip())
            room_price = float(file.readline().strip())

            income_before_tax = bookings * room_price
            tax = income_before_tax * tax_rate

            print(f"\nRoom: {room_type}")
            print(f"Number of bookings: {bookings}")
            print(f"Room price: {room_price:.2f}")
            print(f"Income before tax: {income_before_tax:.2f}")
            print(f"Tax: {tax:.2f}")

            total_income_before_tax += income_before_tax
            total_tax += tax

    print(f"\nTotal income before tax: {total_income_before_tax:.2f}")
    print(f"Total tax: {total_tax:.2f}")

if __name__ == "__main__":
    main()
