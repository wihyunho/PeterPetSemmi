router.post('/imageUpload',multipartMiddleware,function(req,res){
    f = fs.readFileSync(req.files.file.path);
    base64 = Buffer.from(f).toString('base64');
    
    var imgbbAPI = require('../imgbbAPIkey.json'); // API KEY
    const options = {
        uri:'https://api.imgbb.com/1/upload?expiration=600&key='+imgbbAPI.key, 
        method: 'POST',
        form: {
          image:base64, // 이미지 첨부
        },
        json: true // json 형식으로 응답
    }
    request.post(options, function(error,httpResponse,body){
        if(error){
            res.send({error: error});
        } else{
            res.send({url: body.data.display_url});
        }
    });
});