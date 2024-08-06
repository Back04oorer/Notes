import os
import sys
import json
import datetime
import re
from datetime import datetime, timedelta

def setup():
    # Expand user's home directory and read JSON file
    with open(os.path.expanduser('~/.jafr/user-settings.json'), 'r') as file:
        data = json.load(file)
        
    master_path = data['master']

    if not os.path.exists(master_path):
        sys.stderr.write("Jafr's chosen master directory does not exist.\n")
        sys.exit()

    meetings_file_path = get_meetings_file(master_path)
    tasks_file_path = get_task_file(master_path)

    if not os.path.exists(meetings_file_path):
        sys.stderr.write("Missing tasks.md or meetings.md file.\n")
        sys.exit()
    if not os.path.exists(tasks_file_path):
        sys.stderr.write("Missing tasks.md or meetings.md file.\n")
        sys.exit()       

    return master_path
    
def display_menu():
    print("What would you like to do?")
    print("1. Complete tasks")
    print("2. Add a new meeting.")
    print("3. Share a task.")
    print("4. Share a meeting.")
    print("5. Change Jafr's master directory.")
    print("6. Exit")

def display_tasks(master_path: str):
    # 获取今天的日期
    today = datetime.today()

    # 将日期格式化为 dd/mm/yy
    formatted_date = today.strftime('%d/%m/%y')

    tasks_due_today = []
    tasks_due_next_three_days = []
    tasks_due_next_three_days_due = []

    tasks_file = get_task_file(master_path)
    # 定义匹配每一行的正则表达式
    task_pattern = re.compile(r'- (.*?) Due: (\d{2}/\d{2}/\d{2}) (not complete|complete)')

    with open(tasks_file, 'r') as file:
        for line in file:
            match = task_pattern.match(line.strip())
            if match:
                task_desc, date_str, task_status = match.groups()
                time = convert_to_datetime(date_str)
                today_date = convert_to_datetime(formatted_date)
                three_days_after = today_date + timedelta(days=3)

                if time == today_date and task_status == "not complete":
                    tasks_due_today.append(task_desc)

                elif today_date < time <= three_days_after and task_status == "not complete":
                    tasks_due_next_three_days.append(task_desc)
                    tasks_due_next_three_days_due.append(date_str)

    print("Just a friendly reminder! You have these tasks to finish today.")
    for task in tasks_due_today:
        print(f"- {task}")
    
    print("\nThese tasks need to be finished in the next three days!")
    for task, due_date in zip(tasks_due_next_three_days, tasks_due_next_three_days_due):
        print(f"- {task} by {due_date}")

    return 

def display_meetings(master_path: str):
    # 获取今天的日期
    today = datetime.today()

    # 将日期格式化为 dd/mm/yy
    formatted_date = today.strftime('%d/%m/%y')

    meetings_times_today = []
    meetings_desc_today = []

    meetings_desc_upcoming7 = []
    meetings_time_upcoming7 = []
    meetings_date_upcoming7 = []

    meetings_file = get_meetings_file(master_path)

    # 定义匹配每一行的正则表达式
    meeting_pattern = re.compile(r'- (.*?) Scheduled: (\d{2}:\d{2}) (\d{2}/\d{2}/\d{2})')

    with open(meetings_file, 'r') as file:
        for line in file:
            match = meeting_pattern.match(line.strip())
            if match:
                meetings_desc, time, date_str = match.groups()
                date = convert_to_datetime(date_str)
                today_date = convert_to_datetime(formatted_date)
                seven_days_after = today_date + timedelta(days=7)

                if date == today_date:
                    meetings_desc_today.append(meetings_desc)
                    meetings_times_today.append(time)
                elif today_date < date <= seven_days_after:
                    meetings_date_upcoming7.append(date_str)
                    meetings_time_upcoming7.append(time) 
                    meetings_desc_upcoming7.append(meetings_desc)
    
    print("You have the following meetings today!")
    i = 0
    while i < len(meetings_desc_today):
        print(f"- {meetings_desc_today[i]} at {meetings_times_today[i]}")
        i+=1
    
    print("\nYou have the following meetings scheduled over the next week!")
    i = 0
    while i < len(meetings_date_upcoming7):
        print(f"- {meetings_desc_upcoming7[i]} on {meetings_date_upcoming7[i]} at {meetings_time_upcoming7[i]}")
        i+=1

    return

def complete_tasks(master_path):

    tasks_file = get_task_file(master_path)

    # Updated regex pattern to capture any leading whitespace (indentation)
    task_pattern = re.compile(r'(\s*)- (.*?) Due: (\d{2}/\d{2}/\d{2}) (not complete|complete)')

    tasks = []
    with open(tasks_file, 'r') as file:
        for line in file:
            tasks.append(line)

    incomplete_tasks = [line for line in tasks if task_pattern.match(line) and "not complete" in line]

    if not incomplete_tasks:
        print("No tasks to complete!")
        return

    print("Which task(s) would you like to mark as completed?")
    count = 1
    for idx, line in enumerate(tasks):
        match = task_pattern.match(line.strip())
        if match:
            indent, task_desc, date_str, task_status = match.groups()
            if task_status == "not complete":
                print(f"{count}. {task_desc} by {date_str}")
                count += 1

    input_get = input()
    while True:
        status, numbers = check_if_valid_selections(input_get, count)
        if status:
            break
        else:
            input_get = input()
  
    count = 1
    for idx, line in enumerate(tasks):
        match = task_pattern.match(line)
        if match:
            indent, task_desc, date_str, task_status = match.groups()
            if task_status == "not complete" and count in numbers:
                # Keep the original indentation when updating the task
                tasks[idx] = f"{indent}- {task_desc} Due: {date_str} complete\n"
                count += 1
            elif task_status == "not complete":
                count += 1

    with open(tasks_file, 'w') as file:
        file.writelines(tasks)
    print("Marked as complete.")
    return



def add_meetings(master_path,passwd_path):
    input_desc = input("Please enter a meeting description:\n")
    while True:
        if not input_desc.strip() == "":
            break
        input_desc = input("Empty description,please try again\n")
    
    input_date = input("Please enter a date:\n")
    while True:
        if is_valid_date(input_date):
            break
        input_date = input("Invalid date.Please enter a date like <DD/MM/YY>\n")
    
    input_time = input("Please enter a time:\n")  
    while True:
        if is_valid_time(input_time):
            break
        input_time = input("Invalid time.Please enter a time like <HH:MM>\n")
    
    meetings_file = get_meetings_file(master_path)

    with open(meetings_file, 'a') as file:
        file.write("\n##### added by you\n")
        file.write(f"- {input_desc} Scheduled: {input_time} {input_date}\n")

    print(f"Ok, I have added {input_desc} on {input_date} at {input_time}.")

    input_get =input("Would you like to share this meeting? [y/n]: ")

    if input_get == "y":
        meetings_file = get_meetings_file(master_path)
        usernames,user_ids,home_dirs = read_passwd(master_path,passwd_path)
            
        print("Who would you like to share with?")
        i = 0
        while i < len(usernames):
            print(f"{user_ids[i]} {usernames[i]}")
            i+=1

        input_get = input()
        while True:
            if input_get != "":
                break
            input_get =input()

        while True:
            selected_ids = input_get.split()
            i = 0
            indexs = [] 
            while i < len(selected_ids):
                try:
                    index = user_ids.index(selected_ids[i])
                    indexs.append(index)

                except ValueError:
                    input_get = input("Invalid input.Please enter right id(s)\n")
                    break
                
                i += 1
            if i == len(selected_ids):
                break 

        username_current = os.environ.get('USER')

        i = 0 
        while i < len(selected_ids):
            with open(os.path.expanduser(f'{home_dirs[indexs[i]]}/.jafr/user-settings.json'), 'r') as file:
                data = json.load(file)
            master_path_tem = data['master']
            meetings_file_path = get_meetings_file(master_path_tem)
            with open(meetings_file_path, 'a') as file:
                file.write(f"\n##### shared by {username_current}\n")
                file.write(f"- {input_desc} Scheduled: {input_time} {input_date}\n")

            i+=1
        
        print("Meeting shared.")
        return 

    elif input_get == "n":
        return

def share_tasks(master_path, passwd_path):

    tasks_file = get_task_file(master_path)
    
    # 定义匹配每一行的正则表达式
    task_pattern = re.compile(r'(\s*)- (.*?) Due: (\d{2}/\d{2}/\d{2}) (not complete|complete)')

    tasks = []
    with open(tasks_file, 'r') as file:
        for line in file:
            tasks.append(line)

    print("Which task would you like to share?")
    count = 1
    for idx, line in enumerate(tasks):
        match = task_pattern.match(line.strip())
        if match:
            indent, task_desc, date_str, task_status = match.groups()
            print(f"{count}. {task_desc} by {date_str}")
            count += 1

    input_get = input()
    while True:
        status, numbers = check_if_valid_selections(input_get, count)
        if status:
            break
        input_get = input()

    selected_task = []
    count = 1

    for line in tasks:
        match = task_pattern.match(line)
        if match:
            if count in numbers:
                selected_task.append(line)
            count += 1

    ########################################################################################
    usernames, user_ids, home_dirs = read_passwd(master_path, passwd_path)
        
    print("Who would you like to share with?")
    i = 0
    while i < len(usernames):
        print(f"{user_ids[i]} {usernames[i]}")
        i += 1

    input_get = input()
    while True:
        if input_get != "":
            break
        input_get = input("Invalid input. Try again")  
    
    while True:
        selected_ids = input_get.split()
        i = 0
        indexes = [] 
        while i < len(selected_ids):
            try:
                index = user_ids.index(selected_ids[i])
                indexes.append(index)
            except ValueError:
                input_get = input("Invalid input. Please enter the right id(s)\n")
                break
            i += 1
        if i == len(selected_ids):
            break
    
    username_current = os.environ.get('USER')

    i = 0 
    while i < len(selected_ids):
        with open(os.path.expanduser(f'{home_dirs[indexes[i]]}/.jafr/user-settings.json'), 'r') as file:
            data = json.load(file)
        
        master_path_tem = data['master']
        tasks_file_path = get_task_file(master_path_tem)
        j = 0
        while j < len(selected_task):
            with open(tasks_file_path, 'a') as file:
                file.write(f"\n##### shared by {username_current}\n")
                file.write(selected_task[j])
                j += 1
        i += 1
    
    print("Task shared.")
    return 
 

def share_meetings(master_path,passwd_path):

    meetings_file = get_meetings_file(master_path)

    # 定义匹配每一行的正则表达式
    meeting_pattern = re.compile(r'- (.*?) Scheduled: (\d{2}:\d{2}) (\d{2}/\d{2}/\d{2})')

    meetings = []
    with open(meetings_file, 'r') as file:
        for line in file:
            meetings.append(line)

    print("Which meeting would you like to share?")
    count = 1
    for idx, line in enumerate(meetings):
        match = meeting_pattern.match(line.strip())
        if match:
            meetings_desc, time,date_str = match.groups()
            print(f"{count}. {meetings_desc} on {date_str} at {time}")
            count += 1
    
    input_get = input()
    while True:
        status,numbers = check_if_valid_selections(input_get,count)
        if status:
            break
        input_get = input()

    selected_meetings = []
    count = 1

    for line in meetings:
        match = meeting_pattern.match(line.strip())
        if match:
            if count in numbers:
                selected_meetings.append(line)
            count += 1

   ########################################################################################
    usernames,user_ids,home_dirs = read_passwd(master_path,passwd_path)
        
    print("Who would you like to share with?")
    i = 0
    while i < len(usernames):
        print(f"{user_ids[i]} {usernames[i]}")
        i+=1

    input_get = input()
    while True:
        if input_get != "":
            break
        input_get =input()

    while True:
        selected_ids = input_get.split()
        i = 0
        indexs = [] 
        while i < len(selected_ids):
            try:
                index = user_ids.index(selected_ids[i])
                indexs.append(index)

            except ValueError:
                input_get = input("Invalid input.Please enter right id(s)\n")
                break
            
            i += 1
        if i == len(selected_ids):
            break 

            
    username_current = os.environ.get('USER')


    i = 0 
    while i < len(selected_ids):
        with open(os.path.expanduser(f'{home_dirs[indexs[i]]}/.jafr/user-settings.json'), 'r') as file:
            data = json.load(file)
        master_path_tem = data['master']
        meetings_file_path = get_meetings_file(master_path_tem)
        j = 0
        while j < len(selected_meetings):
            with open(meetings_file_path, 'a') as file:
                file.write(f"\n##### shared by {username_current}\n")
                file.write(selected_meetings[j])
                j += 1
        i+=1
    
    print("Meeting shared.")
    return 

def change_master_directory():
    # 获取用户输入的绝对路径
    input_get = input("Which directory would you like Jafr to use?")

    # 指定JSON文件的路径
    settings_path = os.path.expanduser('~/.jafr/user-settings.json')

    # Step 1: 读取文件
    with open(settings_path, 'r') as file:
        data = json.load(file)

    # Step 2: 修改"master"的值为用户输入的值
    data['master'] = input_get

    # Step 3: 将修改后的数据写回文件
    with open(settings_path, 'w') as file:
        json.dump(data, file) 

    # 输出确认消息
    print()
    print(f"Master directory changed to {input_get}.")
    return

############################################################################################################
##here are some support functions
############################################################################################################
def check_if_valid_selections(input_get,count):
    if input_get == "":
        print("Invalid input. Please enter integers like <1 3 5>.")       
        return False,[]

    try:
        numbers = list(map(int, input_get.split()))
        for num in numbers:
            if num >= count or num <= 0:
                print("Out of range.")
                return False,numbers# Exit the function if out of range
        return True,numbers

    except ValueError as e:
        print("Invalid input. Please enter integers like <1 3 5>.")
        return False,[]

def read_passwd(master_path,passwd_path):
    user_pattern = re.compile(r'^([^:]+):[^:]+:([^:]+):[^:]+:[^:]+:([^:]+):', re.MULTILINE)

    current_username = os.environ.get('USER')
    with open(passwd_path, 'r') as file:  # 注意这里已经修改为使用passwd变量的值
        content = file.read()
        matches = user_pattern.findall(content)

        usernames = []
        user_ids = []
        home_dirs = []

        for match in matches:
            username, user_id, home_dir = match
            
            # 如果用户名与当前用户名相同，则跳过
            if username == current_username:
                continue
            
            usernames.append(username)
            user_ids.append(user_id)
            home_dirs.append(home_dir)
        
        return usernames, user_ids, home_dirs

def convert_to_datetime(date_str):
    # Split the date string into components
    day, month, year = date_str.split('/')

    # Convert 2-digit year to 4-digit year
    if 70 <= int(year) <= 99:
        year = '19' + year
    else:
        year = '20' + year

    # Convert the string to a datetime object
    date_obj = datetime.strptime(f"{year}-{month}-{day}", "%Y-%m-%d")
    
    return date_obj

def is_valid_date(date_str):
    try:
        # 尝试将字符串解析为日期
        datetime.strptime(date_str, '%d/%m/%y')
        return True
    except ValueError:
        # 如果解析失败，则返回False
        return False

def is_valid_time(time_str):
    if len(time_str) != 5 or time_str[2] != ':':
        return False

    try:
        datetime.strptime(time_str, '%H:%M')
        return True
    except ValueError:
        return False

def get_task_file(path):
    return path + "/tasks.md"

def get_meetings_file(path):
    return path + "/meetings.md"

############################################################################################################
##above are some support functions
############################################################################################################

def main():
    master_path = setup()

    display_tasks(master_path)
    print()
    display_meetings(master_path)
    print()

    while True:
        master_path = setup()
        #print(master_path)
        display_menu()
        passwd_path = sys.argv[1]
        input_get = input()

        if input_get == "1":
            complete_tasks(master_path)
        elif input_get =="2":
            add_meetings(master_path,passwd_path)
        elif input_get == "3":
            share_tasks(master_path,passwd_path)
        elif input_get == "4":
            share_meetings(master_path,passwd_path)
        elif input_get == "5":
            change_master_directory()
        elif input_get == "6":
            sys.exit()
        else:
            print("Invalid command.")
            sys.exit()
    return 
 
if __name__ == '__main__':
    main()
