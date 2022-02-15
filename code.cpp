#include<bits/stdc++.h>
using namespace std;

int getNumber(string temp)
{
    string prev;
    stringstream ss(temp);
    while (ss >> temp)
        prev = temp;
    return stoi(prev);
}

string getName(string temp){
    string res;
    stringstream ss(temp);
    while (ss >> temp){
        if(res.empty())
            res = temp;
        else
            res = res +" "+ temp;
        if(temp.back()==':'){
            res.pop_back();
            return res;
        }
    }
    return res;
}


int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
    int numberOfEmployees=0;
    string temp, prev;
    getline(cin, temp);
    vector<int> prices;
    unordered_map<int, string> gifts;
    numberOfEmployees = getNumber(temp);
    getline(cin, temp);
    getline(cin, temp);
    getline(cin, temp);
    while (getline(cin, temp)){
        if (temp.empty())
            break;
        string name = getName(temp);
        int price = getNumber(temp);
        gifts[price] = name;
        prices.push_back(price);
    }
    sort(prices.begin(), prices.end());
    int minDiff = INT_MAX;
    int start = 0;
    int n = prices.size();
    for (int i = n - 1; i - numberOfEmployees>=0; i--){
        int curDiff = prices[i]-prices[i-numberOfEmployees];
        if(curDiff < minDiff){
            minDiff = curDiff;
            start = i - numberOfEmployees+1;
        }
    }

    cout << "The goodies selected for distribution are:\n\n";
    for (int i = start; i < start + numberOfEmployees; i++)
                cout
         << gifts[prices[i]] << ": " << prices[i] << '\n';

    cout << "And the difference between the chosen goodie with highest price and the lowest price is " << prices[start + numberOfEmployees-1] - prices[start] << '\n';
    return 0;
}