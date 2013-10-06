function(srv_type, collectionName){

 var mapF = function(srv_type){
    if(srv_type == "UT")
        key = this.ut_name;
    else
        key = this.srv_name;

    var value = {
           count: 1,
           duration: this.duration
    };
    emit(key, value);
 };
 var reduceF = function(key, values){
    reducedVal = { count: 0, totalDuration: 0, averageDuration:0 };
    for (var idx = 0; idx < values.length; idx++) {
        reducedVal.count += values[idx].count;
        reducedVal.totalDuration += values[idx].duration;
    }
    reducedVal.averageDuration = reducedVal.totalDuration / reducedVal.count;
    return reducedVal;
 };
db.plf.mapReduce( mapF, reduceF,
    {
        out: { merge: collectionName },
        query: {srv_type: srv_type, "duration": {"$ne": null},"action_type": "end"}
    }
);
}