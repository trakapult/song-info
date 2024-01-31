const {Song} = require('../models')

module.exports = {
    async index (req, res) {
        try {
            const song = await Song.findAll({ // await is necessary
                limit: 10
            })
            res.send(song)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to fetch the songs'
            })
        }
    },
    async show (req, res) {
        try {
            console.log(req.params.songId)
            const song = await Song.findByPk(req.params.songId) // await is necessary
            res.send(song)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to fetch the song'
            })
        }
    },
    async post (req, res) {
        try {
            const song = await Song.create(req.body) // await is necessary
            res.send(song)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to create the song'
            })
        }
    },
    async put (req, res) {
        try {
            const song = await Song.update(req.body, {
                where: {
                    id: req.params.songId
                }
            }) // await is necessary
            console.log(req.body)
        } catch (err) {
            res.status(500).send({
                error: 'An error has occured trying to update the song'
            })
        }
    }
}