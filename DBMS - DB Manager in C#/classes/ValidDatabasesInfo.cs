/***************************************************************************/
using System;
using System.Collections;

using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
/***************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// ValidConnection class stores a valid combination of 
	/// username, password and database name
	/// </summary>
/***************************************************************************/
	[Serializable]
	public class ValidDatabasesInfo
	{
/***************************************************************************/
		Hashtable databases;
/***************************************************************************/
		public ValidDatabasesInfo()
		{
			//read from file and initialize else initialize hard coded values
			try 
			{
				IFormatter formatter = new BinaryFormatter();
				Stream stream = new FileStream("valid_databases.bin", FileMode.Open, FileAccess.Read, FileShare.Read);
				databases = (Hashtable) formatter.Deserialize(stream);
				stream.Close();
			}
			catch
			{
				databases = new Hashtable();
				//add some sample database details and users
//				addUser("ncst", "yogesh", "yogesh");
//				addUser("ncst", "ruchi", "ruchi");
//				addUser("cdac", "mittal", "mittal");
//				addUser("cdac", "amar", "amar");
				addUser("a", "a", "a");
				addUser("a", "s", "s");
				addUser("d", "a", "a");
				addUser("d", "s", "s");
				saveValidDatabasesInfo();
			}
		}
/***************************************************************************/
		public void saveValidDatabasesInfo()
		{
			try 
			{
				IFormatter formatter = new BinaryFormatter();
				Stream stream = new FileStream("valid_databases.bin", FileMode.Create, FileAccess.Write, FileShare.None);
				formatter.Serialize(stream, databases);
				stream.Close();
			}
			catch
			{
			}
		}
/***************************************************************************/
		//validates a user and returns appropriate mesage.
		public string valdiateUser(string db, string uname, string pwd)
		{
			DatabaseDetails dbDetails = (DatabaseDetails)databases[db];
			if (dbDetails == null)
				return "Database '" + db + "' " + "does not exist.";

			DatabaseUser user = (DatabaseUser)dbDetails.users[uname];
			if (user == null)
				return "User '" + uname + "' " + "does not exist.";

			if (user.username.ToUpper().Equals(uname.ToUpper()) && 
				user.password.ToUpper().Equals(pwd.ToUpper()))
				return "SUCCESS";

			return "Password entered is invalid.";
		}
/***************************************************************************/
		public void addUser(string db, string uname, string pwd)
		{
			DatabaseDetails dbDetails = (DatabaseDetails)databases[db];
			if (dbDetails == null)
			{
				//add database first and then user
				dbDetails = new DatabaseDetails(db);
				databases.Add(db, dbDetails);
			}
			dbDetails.addUser(db, uname, pwd);
			
		}
/***************************************************************************/
	}
/*#########################################################################################*/
	[Serializable]
	public class DatabaseDetails
	{
/***************************************************************************/
		public string database_name;
		public Hashtable users;
/***************************************************************************/
		public DatabaseDetails(string db)
		{
			this.database_name = db;
			users = new Hashtable();
		}
/***************************************************************************/
		public void addUser(string db, string uname, string pwd)
		{
			DatabaseUser user = (DatabaseUser)users[uname];
			if (user != null)
			{
				users.Remove(uname);
			}
			users.Add(uname, new DatabaseUser(uname, pwd, db));
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