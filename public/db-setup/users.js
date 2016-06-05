db.users.drop();

db.users.insert({
	first_name:"Tomasz",
	second_name:"Koszlowski",
	password:"qwe",
	email:"q@w",
	job_position:"Software developer",
  location:"London",
  skills:["java", "scala", "akka", "play"],
  interests:["Music, IT"],
	suggested_events:[],
  upcoming_events:[],
  past_events:[]
  }
);
db.users.insert({
	first_name:"John",
	second_name:"Doe",
	password:"qwe",
	email:"a@w",
	job_position:"CEO",
  location:"Zurich",
  skills:["java", "scala", "akka", "play"],
  interests:["Music, IT"],
	suggested_events:[],
  upcoming_events:[],
  past_events:[]
  }
);
db.users.insert({
	first_name:"Harry",
	second_name:"Zhang",
	password:"1111",
	email:"harry@w",
	job_position:"CEO",
  location:"Shenzhen",
  skills:["events", "IoT"],
  interests:["Music, IT"],
	suggested_events:[],
  upcoming_events:[],
  past_events:[]
  }
);
