db.event.drop()
db.user.drop()
db.topics.drop()

db.topics.insert(
    {
        name:"IT",
        tags:["db", "java", "jvm","scala","programming","c++","js","javascript","nodejs","web","html","mobile"]
    },
    {
        name:"Sport",
        tags:["badminton","football","fitness","bike","running","triathlon","tennis"]
    },
    {
        name:"Language",
        tags:["english","chinese"]
    },
    {
        name:"Business",
        tags:["co-working space", "project management", "startups", "investment"]
    }
)

db.event.createIndex({"slug":1},{unique:true})

db.event.insert({
	event_name:"AngelHack",
  slug:"angel2015",
	topic:["IT"],
	image_url:"",
	time:"14-15 November 2015",
	address:"Shenzhen, China",
	description:"We are also planning on hosting the Global CodeRetreat in our EPAM HackFest Shenzhen on 14 November, 2015. If you would like to take part in this  focus practice, enjoy  zen code exercises to improve your code quality, please apply at : https://epa.ms/CodeRetreatSZ2015"
});

ev = db.event.findOne({slug:"epamhackfest2015"})

db.user.update(
  {user_id:"1"},
  {
    $addToSet: { events:ObjectId("56cb170bb6ae74571d0a44b9")}
  }
);

db.suggestions.insert(
  [
    {
      event_id:1,
      tags:["a","b"],
      user_id:[11,90]
    },
    {
      event_id:2,
      tags:["cc","dd"],
      user_id:[11,34]
    },
    {
      event_id:3,
      tags:["eee","fff"],
      user_id:[11,34,55,77]
    }
  ]
);

db.user.insert({
	first_name:"Tomasz",
	second_name:"Koszlowski",
	password:"12345",
	email:"q@w",
	sex:"M",
	job_position:"Software developer",
  location:"London",
  skills:["java", "scala", "akka", "play"],
  interests:["Music, IT"],
	suggested_events:[],
  upcoming_events:[],
  past_events:[]
  }
);

db.user.insert({
	first_name:"Michal",
	second_name:"Zurek",
	password:"12345",
	email:"q1@w",
	sex:"M",
	job_position:"CTO",
  location:"HK",
  skills:["java", "scala", "akka", "play"],
  interests:["Music, IT"],
	suggested_events:[],
  upcoming_events:[],
  past_events:[]
  }
);
