n = int(input("Please enter the number of jobs: "))
jobs = []
for i in range(n):
    start_time = int(input("Please enter start time: "))
    end_time = int(input("Please enter end time: "))
    profit = int(input("Please enter profit value: "))
    jobs.append([start_time, end_time, profit])

#Sort the jobs in ascending order of start time
jobs.sort(key = lambda x:x[0])

#Lokesh picks job with maximum profit
max_profit = 0
for job in jobs:
    if job[2] > max_profit:
        max_profit = job[2]
        max_job = job

#Remove the selected job from the jobs list
jobs.remove(max_job)

#Calculate the number of jobs and total earnings left for other employees
number_jobs = len(jobs)
total_earnings = 0
for job in jobs:
    total_earnings += job[2]

print("Number of jobs left for other employees: ", number_jobs)
print("Total earnings left for other employees: ", total_earnings)
