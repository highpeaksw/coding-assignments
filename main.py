def combine(inp):
    return combine_helper(inp, [], [])

class my_dictionary(dict):
    def init(self):
        self = dict()

    def add(self, key, value):
        self[key] = value

def combine_helper(inp, temp, ans):
    for i in range(len(inp)):
        current = inp[i]
        remaining = inp[i + 1:]
        temp.append(current)
        ans.append(tuple(temp))
        combine_helper(remaining, temp, ans)
        temp.pop()
    return ans

count = 0;
goodieDict = my_dictionary()
file1 = open("sample_input.txt","r")
for x in file1:
    count+=1
    if count>=5 :
        (key, val) = x.split(": ")
        if val[-1]=="\n":
            val = val.rstrip(val[-1])
        goodieDict[(key)] = val;
    elif count==1 :
        (numEmpTxt, numEmp) = x.split(": ")

print(goodieDict)
priceList = list(goodieDict.values())
for i in range(0, len(priceList)):
    priceList[i] = int(priceList[i])

dict_obj = my_dictionary()
a = combine(priceList)
for i in a:
    if(len(i) == (int(numEmp))):
        nt = sorted(i)
        diffValue = nt[(int(numEmp)-1)] - nt[0]
        dict_obj.key = diffValue
        dict_obj.value = nt
        dict_obj.add(dict_obj.key, dict_obj.value)

keyList = dict_obj.keys()
sortedKeyList = sorted(keyList)

finalPriceList = dict_obj.get(sortedKeyList[0])

f = open("sample_output.txt", "w")
f.write("The goodies selected for distribution are:\n\n")
for pricee in finalPriceList:
    for keey in goodieDict.keys():
        if pricee == int(goodieDict[keey]):
            f.write(keey+": "+str(pricee)+"\n")
f.write("\n")

differencePrice = finalPriceList[(int(numEmp)-1)] - finalPriceList[0]
f.write("And the difference between the chosen goodie with highest price and the lowest price is "+str(differencePrice))
f.close()