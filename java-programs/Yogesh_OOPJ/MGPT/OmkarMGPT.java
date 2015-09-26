import ncst.pgdst.*;
public class OmkarMGPT 
{
	void recurse(int cnt, int ic)
	{
		for(int i = ic+1 ; i<n; i++)
		{
			if !(obj[i].mark)   obj[i].mark_obj();     
			//* this is for ensuring not marked actually not needed *//
			if (cnt < select)
				recurse (cnt++, i);
			else 
				calc_days(list_cnt++);
			obj[i].unmark_obj();
		}
	}
	void calc_days(int cnt)
	{
		for(i=0; i<n; i++)
		{
			if (obj[i].mark)
			/*check days by string comparison and mark if not marked also 
			add counter for days if not marked*/
		}
		//* store marked subj in Global_List(cnt) and no_of_days also
		//* unmark all days
	}
	//*declare global counters list_cnt; select; n;
	// select is for no.of subjects to be selected
	//* call recurse from main function as recurse(1, -1)
}
