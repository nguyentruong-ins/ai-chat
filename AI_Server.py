import g4f
from flask import Flask
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)

class HelloWord(Resource):
    def get(self, message):
        response = g4f.ChatCompletion.create(model='gpt-3.5-turbo', provider=g4f.Provider.DeepAi, messages=[
                                        {"role": "user", "content": message}], stream=False)
        reply = ""
        for ans in response:
            reply += ans
        return (reply)
    
api.add_resource(HelloWord, '/helloworld/<string:message>')

if __name__ == '__main__':
    app.run(debug=True)