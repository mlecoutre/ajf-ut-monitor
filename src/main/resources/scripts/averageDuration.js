 var mapF = function(){
    emit(this.ut_name, this.duration);
 };
 var reduceF = function(key, values){
    return  Array.sum(values) / values.length}
 };
db.plf.mapReduce( mapF, reduceF,
    {
        out: { merge: "ut_duration" },
        query: {srv_type: "UT", "duration": {"$ne": null},"action_type": "end"}
    }
);
