function(srv_type, collectionName){

 var mapF = function(srv_type){
    if(srv_type == "UT")
        key = this.ut_name;
    else
        key = this.srv_name;

    emit(key, this.duration);
 };
 var reduceF = function(key, values){
    return  Array.sum(values) / values.length;
 };
db.plf.mapReduce( mapF, reduceF,
    {
        out: { merge: collectionName },
        query: {srv_type: srv_type, "duration": {"$ne": null},"action_type": "end"}
    }
);
}