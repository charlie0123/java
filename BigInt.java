

package Project1;

import java.util.ArrayList;

public class BigInt {
	private boolean positive = true;

	public ArrayList<Integer> numList = new ArrayList<Integer>();

	public BigInt() {
		this.positive = true;
	}

	public BigInt(String constructorInput) throws Exception {
		this.setSignAndRemoveIfItIsThere(constructorInput);
	}

	public void setSignAndRemoveIfItIsThere(String input) throws Exception {
		char[] temp = input.toCharArray();

		for (int i = 0; i < temp.length; i++) {
			char currentchar = temp[i];
			if (!(currentchar == 45 || currentchar == 43 || (currentchar >= 48 && currentchar <= 57))) {

				throw new Exception("Invalid character");
			}
		}

		for (int i = 0; i < temp.length; i++) {
			char currentchar = temp[i];
			if ((i == 0) && ((currentchar == 43) || (currentchar == 45))) {
				if (temp.length == 1) {

					throw new Exception("Enter Number");
				}

				if (currentchar == '-') {

					this.positive = false;
				} else {
					this.positive = true;

				}
				continue;
			}
			this.numList.add((int) currentchar);
		}
	}

	public String toString() {
		return this.listToString(this.numList) + "";
	}

	public String listToString(ArrayList<Integer> intList) {
		StringBuilder sb = new StringBuilder();
		for (int s : intList) {
			sb.append(Character.toString((char) s));
		}

		return sb.toString();
	}

	private int AsciiToNumeric(int asciiValue) {
		{
			int asciiToNumeric = -1;
			if ((asciiValue >= 48) && (asciiValue <= 57)) {
				asciiToNumeric = asciiValue - 48;
			}

			return asciiToNumeric; // output;
		}
	}

	private int NumericToAscii(int numList) {
		{
			int numericToAscii = 0;
			if ((numList >= 0) && (numList <= 9)) {
				numericToAscii = numList + 48;
			}

			return numericToAscii; // output;
		}
	}

	private ArrayList<Integer> PreceedNZeroes(int n, ArrayList<Integer> inputList) {
		ArrayList<Integer> outputList = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			outputList.add(48);
		}

		outputList.addAll(inputList);

		return outputList;
	}

	public ArrayList<Integer> ExceedNZeroes(ArrayList<Integer> inputList) {
		ArrayList<Integer> outputList = new ArrayList<Integer>();

		for (int i = 0; i <= inputList.size() - 1; i--) {
			outputList.add(48);
		}

		outputList.addAll(inputList);

		return outputList;
	}

	private ArrayList<Integer> Reverse(ArrayList<Integer> inputList) {
		ArrayList<Integer> outputList = new ArrayList<Integer>();
		for (int i = inputList.size() - 1; i >= 0; i--) {
			outputList.add(inputList.get(i));
		}

		return outputList;
	}

	private ArrayList<Integer> Sum(ArrayList<Integer> arglist1, ArrayList<Integer> arglist2) {
		int addnoOfZeros = Math.abs(arglist1.size() - arglist2.size());

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		if (arglist1.size() < arglist2.size()) {
			list1 = this.PreceedNZeroes(addnoOfZeros, list1);
		}
		list1.addAll(arglist1);

		ArrayList<Integer> list2 = new ArrayList<Integer>();
		if (arglist2.size() < arglist1.size()) {
			list2 = this.PreceedNZeroes(addnoOfZeros, list2);
		}
		list2.addAll(arglist2);

		ArrayList<Integer> temp = new ArrayList<Integer>();
		int carry = 0;
		for (int j = list1.size() - 1; j >= 0; j--) {

			int ascii1 = 48;
			ascii1 = list1.get(j);

			int ascii2 = 48;
			ascii2 = list2.get(j);

			int num1 = this.AsciiToNumeric(ascii1);
			int num2 = this.AsciiToNumeric(ascii2);
			int total = num1 + num2 + carry;

			int remainder = total;
			if ((total >= 0) && (total <= 9)) {
				carry = 0;
			} else if (total > 9) {
				carry = 1;
				remainder = total - 10;
			}

			int ascii3 = this.NumericToAscii(remainder); // 57
			temp.add(ascii3);
		}

		if (carry > 0) {
			int carryLast = this.NumericToAscii(carry);
			temp.add(carryLast);
		}

		return this.Reverse(temp);
	}

	public BigInt add(BigInt input) {
		BigInt output = new BigInt();

		output.numList = this.Sum(this.numList, input.numList);

		return output;
	}

	private ArrayList<Integer> Deduct(ArrayList<Integer> arglist1, ArrayList<Integer> arglist2) {

		ArrayList<Integer> output = new ArrayList<Integer>();

		ArrayList<Integer> temp = new ArrayList<Integer>(arglist1);

		// for (int j = deductList1.size() - 1; j >= 0; j--) {
		// int ascii1 = 48;
		// ascii1 = temp.get(j);
		//
		// int ascii2 = 48;
		// ascii2 = deductList2.get(j);
		//
		// int num1 = this.AsciiToNumeric(ascii1);
		// int num2 = this.AsciiToNumeric(ascii2);

		/////
		int addnoOfZeros = Math.abs(arglist1.size() - arglist2.size());

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		if (arglist1.size() < arglist2.size()) {
			list1 = this.PreceedNZeroes(addnoOfZeros, list1);
		}
		list1.addAll(arglist1);

		ArrayList<Integer> list2 = new ArrayList<Integer>();
		if (arglist2.size() < arglist1.size()) {
			list2 = this.PreceedNZeroes(addnoOfZeros, list2);
		}
		list2.addAll(arglist2);

		for (int j = list1.size() - 1; j >= 0; j--) {

			int ascii1 = 48;
			ascii1 = list1.get(j);

			int ascii2 = 48;
			ascii2 = list2.get(j);

			int num1 = this.AsciiToNumeric(ascii1);
			int num2 = this.AsciiToNumeric(ascii2);
			/////

			int outputNum = 0;
			if (num1 >= num2) {
				outputNum = num1 - num2;
			} else {
				outputNum = 10 + num1 - num2;
				int oldValue = temp.get(j - 1);
				temp.set(j - 1, oldValue - 1);
			}
			int ascii3 = this.NumericToAscii(outputNum);
			output.add(ascii3);
		}

		output = this.Reverse(output);
		return output;
	}

	public BigInt subtract(BigInt input) {
		BigInt output = new BigInt();
		int addnoOfZeros = Math.abs(this.numList.size() - input.numList.size());

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		if (addnoOfZeros == 0) {
			for (int i = 0; i < input.numList.size(); i++) {
				int callerValue = this.numList.get(i);
				int inputValue = input.numList.get(i);

				if (callerValue > inputValue || (callerValue == inputValue) && i == input.numList.size() - 1) {
					list1.addAll(this.numList);
					list2.addAll(input.numList);
					break;
				} else if (callerValue < inputValue) {
					list1.addAll(input.numList);
					list2.addAll(this.numList);
					break;
				}
			}
		} else {
			list2 = this.PreceedNZeroes(addnoOfZeros, list2);
			if (this.numList.size() > input.numList.size()) {
				list1.addAll(this.numList);
				list2.addAll(input.numList);

			} else if (this.numList.size() < input.numList.size()) {
				list1.addAll(input.numList);
				list2.addAll(this.numList);
			}
		}

		output.numList = this.Deduct(list1, list2);

		return output;
	}

	public ArrayList<Integer> Product(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> rowTotal = null;
		ArrayList<Integer> finalTotal = new ArrayList<Integer>();
		finalTotal.add(48);

		for (int i = list2.size() - 1, succeedingZeros = 0; i >= 0; i--, succeedingZeros++) {
			rowTotal = new ArrayList<Integer>();
			rowTotal = this.PreceedNZeroes(succeedingZeros, rowTotal);

			int carry = 0;
			for (int j = list1.size() - 1; j >= 0; j--) {
				int list2AsciiValue = list2.get(i);
				int list2Value = this.AsciiToNumeric(list2AsciiValue);

				int list1AsciiValue = list1.get(j);
				int list1Value = this.AsciiToNumeric(list1AsciiValue);

				int total = (list1Value * list2Value) + carry;

				int remainder = total;
				if ((total >= 0) && (total <= 9)) {
					carry = 0;
				} else if (total > 9) {
					carry = total / 10;
					remainder = total % 10;
				}

				int remainderAscii = this.NumericToAscii(remainder);
				rowTotal.add(remainderAscii);
			}

			if (carry > 0) {
				int carryAscii = this.NumericToAscii(carry);
				rowTotal.add(carryAscii);
			}

			rowTotal = this.Reverse(rowTotal);
			finalTotal = this.Sum(finalTotal, rowTotal);
		}

		return finalTotal;
	}

	public BigInt multiply(BigInt input) {
		BigInt output = new BigInt();

		output.numList = this.Product(this.numList, input.numList);

		return output;
	}

	// public int Power(int a, int n) {
	// if (n == 0) {
	// return 1;
	// }
	//
	// return a * Power(a, n - 1);
	// }

	public int Power1(int a, int n) {

		int x = 1;
		for (int i = 0; i <= n - 1; i++) {
			x = a * x;
		}
		return x;
	}

	public ArrayList<Integer> PowerFunction(ArrayList<Integer> base, int n) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(49);
		for (int i = 0; i <= n - 1; i++) {

			temp = this.Product(base, temp);

		}
		return temp;

	}

	public ArrayList<Integer> Power(ArrayList<Integer> a, int n) {
		if (n == 0) {
			ArrayList<Integer> exp = new ArrayList<Integer>();
			exp.add(48);
			return exp;
		} else {
			return this.Product(a, this.Power(a, n - 1));
		}
	}

	public int Compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		//// List 1 < List 2 => -1
		//// List 1 > List 2 => +1
		//// List 1 <= List 2 => 0
		if (list1.size() > list2.size()) {
			return 1;
		} else {
			if (list1.size() < list2.size()) {
				return -1;
			} else {
				if (list1.size() == list2.size()) {
					for (int i = 0; i < list1.size(); i++) {
						if (list1.get(i) < list2.get(i)) {
							return -1;
						} else {
							if (list1.get(i) > list2.get(i)) {
								return 1;
							} else {
								if (list1.get(i) == list2.get(i) && i == list1.size() - 1) {
									return 0;
								}
							}
						}

					}
				}
			}
		}

		return 0;
	}

	public ArrayList<Integer> Division(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		// ArrayList<Integer> output = new ArrayList<Integer>();
		ArrayList<Integer> temp1 = new ArrayList<Integer>(list1);

		ArrayList<Integer> baseList = new ArrayList<Integer>();
		baseList.add(50);
		ArrayList<Integer> partialQuotient = new ArrayList<Integer>();
		partialQuotient.add(48);
		ArrayList<Integer> remainder = new ArrayList<Integer>();
		remainder.add(48);

		ArrayList<Integer> finalQuotient = new ArrayList<Integer>();
		finalQuotient.add(48);

		while (this.Compare(temp1, list2) == 1) // TEMP1 IS GREATER THAN LIST2
		{
			ArrayList<Integer> partialDividend = new ArrayList<Integer>();
			partialDividend.add(48);

			int subLoopCounter = 0;
			while (this.Compare(partialDividend, temp1) == -1) {
				ArrayList<Integer> powValue = this.PowerFunction(baseList, subLoopCounter);
				partialDividend = this.Product(powValue, list2);
				subLoopCounter++;
			}

			ArrayList<Integer> baseRaisedToCounter = this.PowerFunction(baseList, subLoopCounter - 2);
			partialQuotient = this.Product(baseRaisedToCounter, list2);

			temp1 = this.Deduct(temp1, partialQuotient);

			finalQuotient.addAll(baseRaisedToCounter);
		}

		remainder = this.Deduct(partialQuotient, temp1);

		return finalQuotient;
	}

	public BigInt divide(BigInt input) {
		BigInt output = new BigInt();
		output.numList = this.Division(this.numList, input.numList);

		return output;
	}
}
