/***************************************************************************/
using System;
using System.Collections;
/***************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// ValidConnection class stores a valid combination of 
	/// username, password and database name
	/// </summary>
/***************************************************************************/
	[Serializable]
	public class UsersInfo
	{
/***************************************************************************/
		Hashtable users;
/***************************************************************************/
		public UsersInfo()
		{
			users = new Hashtable();
		}
/***************************************************************************/
		public void addUser(string uname, string pwd, string db)
		{
			DatabaseUser dbuser = (DatabaseUser)users[db];
			if (dbuser != null)
			{
				users.Remove(db);				
			}
			users.Add(db, new DatabaseUser(uname, pwd, db));
		}
/***************************************************************************/
	}
/*#########################################################################################*/
	[Serializable]
	public class DatabaseUser
	{
/***************************************************************************/
		public string username;
		public string password;
		public string database;
/***************************************************************************/
		public DatabaseUser(string uname, string pwd, string db)
		{
			this.username = uname;
			this.password = pwd;
			this.database = db;
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/